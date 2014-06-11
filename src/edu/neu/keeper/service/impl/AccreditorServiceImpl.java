package edu.neu.keeper.service.impl;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.KeeperRole;
import edu.neu.keeper.model.LanguageIdentification;
import edu.neu.keeper.model.Registrar;
import edu.neu.keeper.model.RegistrationAuthority;
import edu.neu.keeper.model.Steward;
import edu.neu.keeper.model.Submitter;
import edu.neu.keeper.model.User;
import edu.neu.keeper.service.AccreditorService;
import edu.neu.keeper.service.RepositoryAuthenticationException;
import edu.neu.keeper.service.RepositoryException;

/**
 * Basic implementation of the accreditor interface using JEE features.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Stateless(name="Accreditor")
@WebService(portName = "AccreditorPort",
        serviceName = "AccreditorWebService",
        targetNamespace = "http://mdr.org/wsdl")
public class AccreditorServiceImpl implements AccreditorService {

	@PersistenceContext(unitName="mdr")
	EntityManager entityManager;
	
	@Resource 
	SessionContext context;

	@Override
	public Contact authenticate(String username, String password)
			throws RepositoryAuthenticationException {
		Query query = entityManager.createQuery("from Contact where username=? and password=?");
		query.setParameter(1, username);
		query.setParameter(2, password);
		try {
			return (Contact) query.getSingleResult();
		}
		catch (Exception e) {
			throw new RepositoryAuthenticationException("Wrong credentials for "+username);
		}
	}

	private Contact getContact() throws RepositoryException{
		return entityManager.find(Contact.class, context.getCallerPrincipal().getName());
	}

	
	@Override @RolesAllowed({"accreditor,registrar,steward"})
	public void createReadOnlyUser(User contact,String password) throws RepositoryException {
		contact.setRole(KeeperRole.readOnlyUser);// can we hide this from users?
		contact.setPassword(password);
		try {
			contact.setCreator(getContact());
			entityManager.persist(contact);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
	
	private void createRegistrationAuthority(RegistrationAuthority ra) throws RepositoryException {
		// TODO: checks here for repeated, etc.
		try {
			for(LanguageIdentification langId: ra.getDocumentation_language_identifier()) {
				Query langQuery = entityManager.createQuery("from LanguageIdentification where country_identifier=? and language_identifier=?");
				langQuery.setParameter(1, langId.getCountry_identifier() );
				langQuery.setParameter(2, langId.getLanguage_identifier() );
				LanguageIdentification attachedLangid=null;
				try{ attachedLangid= (LanguageIdentification) langQuery.getSingleResult(); }
				catch (Exception e) {}
				if (attachedLangid==null) {
					entityManager.persist(langId);
				}
				else {
					langId.setId(attachedLangid.getId());
				}
			}
			
			entityManager.persist(ra);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			entityManager.close();
		}
	}


	@Override 	@RolesAllowed("accreditor")
	public void createRegistrar(Registrar registrar,String password)
			throws RepositoryException {
		System.out.println("Entered Create Registar");
		registrar.setPassword(password);
		registrar.setCreator(getContact());
		registrar.setRole(KeeperRole.registrar);// can we hide this from users?
		
		RegistrationAuthority ra = entityManager.find(RegistrationAuthority.class, registrar.getRegistrationAuthority().getOrganization_name());
		if (ra==null) {
			try{
				System.out.println("Entered try");
				createRegistrationAuthority(registrar.getRegistrationAuthority());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				throw new RepositoryException(e);
			}
		}
		try {
			entityManager.persist(registrar);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
		
	}

	@Override @RolesAllowed("registrar")
	public void createSteward(Steward contact,String password) throws RepositoryException {
		contact.setRole(KeeperRole.steward);// can we hide this from users?
		contact.setPassword(password);
		contact.setCreator(getContact());
		try {
			entityManager.persist(contact);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override @RolesAllowed("steward")
	public void createSubmitter(Submitter contact,String password) throws RepositoryException {
		contact.setRole(KeeperRole.submitter);// can we hide this from users?
		contact.setPassword(password);
		contact.setCreator(getContact());
		try {
			entityManager.persist(contact);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void updatePassword(String newPassword) {
		Contact contact = entityManager.find(Contact.class, context.getCallerPrincipal().getName());
		contact.setPassword(newPassword);
		entityManager.persist(contact);
	}

	@Override
	public Contact getSupervisor(String username) throws RepositoryException {
		Query query = entityManager.createQuery("from Contact where username=?");
		query.setParameter(1, username);
		try {
			Contact contact = (Contact) query.getSingleResult();
			Query supquery = entityManager.createQuery("from Contact where username=?");
			supquery.setParameter(1,contact.getCreator().getUsername());
			Contact supervisor  =  (Contact) supquery.getSingleResult();
			return supervisor;
		}
		catch (Exception e) {
			throw new RepositoryAuthenticationException("Wrong credentials for "+username);
		}
	}

	@Override
	public Contact getContactInfo(String username) throws RepositoryException {
		return entityManager.find(Contact.class, username);
	}
	
	@PermitAll //programatic security being used here
	@Override 
	public void updateContactInfo(String username,String contactInfo,String contactName,String contactTitle,String email) throws RepositoryException {
		Contact contact = entityManager.find(Contact.class, username);
		contact.setContact_information(contactInfo);
		contact.setContact_title(contactTitle);
		contact.setEmail(email);
		String caller = context.getCallerPrincipal().getName();
		if (caller.equals("system")||caller.equals("root")) { //|| getSupervisor(contact.getUsername()).equals(caller)) {
			
			entityManager.persist(contact); // this is somewhat restricted.
		}
		else {
			if (!caller.equals(contact.getUsername()))
				throw new RepositoryAuthenticationException("You cannot update another user");
			entityManager.persist(contact);
		}
	}

	@Override
	public void updatePasswordForUser(String user, String newPassword) throws RepositoryException {
		Contact contact = entityManager.find(Contact.class, user);
		contact.setPassword(newPassword);
		String caller = context.getCallerPrincipal().getName();
		if (caller.equals("system")||caller.equals("root") || getSupervisor(user).equals(caller)) {
			entityManager.persist(contact); // this is somewhat restricted.
		}
		else {
			if (!caller.equals(contact.getUsername()))
				throw new RepositoryAuthenticationException("You cannot update another user");
			entityManager.persist(contact);
		}		
	}
}
