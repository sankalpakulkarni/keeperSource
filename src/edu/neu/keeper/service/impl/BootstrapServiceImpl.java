package edu.neu.keeper.service.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.neu.keeper.service.BootstrapService;
import edu.neu.keeper.service.RepositoryException;
import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.KeeperRole;
/**
 * Basic implementation of the bootstrap service interface.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Stateless(name="Bootstrap")
public class BootstrapServiceImpl implements BootstrapService{

	@PersistenceContext(unitName="mdr")
	EntityManager entityManager;
	
	@Override
	public void createDefaultAccreditor() throws RepositoryException {
		try {
			Contact contact = new Contact();
			contact.setContact_information("Accreditor");
			contact.setContact_name("Accreditor");
			contact.setContact_title("Accreditor");
			contact.setUsername("root");
			contact.setPassword("root");
			contact.setRole(KeeperRole.accreditor);
			entityManager.persist(contact);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
	
	@Override
	public void createSystemUser() throws RepositoryException {
		try {
			Contact contact = new Contact();
			contact.setContact_information("System");
			contact.setContact_name("System");
			contact.setContact_title("System");
			contact.setUsername("system");
			contact.setPassword("system");
			contact.setRole(KeeperRole.system);
			entityManager.persist(contact);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

}
