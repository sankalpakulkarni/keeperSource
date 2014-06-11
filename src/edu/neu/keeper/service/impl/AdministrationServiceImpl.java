package edu.neu.keeper.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.KeeperRole;
import edu.neu.keeper.model.KeptItem;
import edu.neu.keeper.model.KeptItemHeader;
import edu.neu.keeper.model.Procedure;
import edu.neu.keeper.service.AccreditorService;
import edu.neu.keeper.service.AdministrationService;
import edu.neu.keeper.service.RepositoryException;
import edu.neu.keeper.service.WorkflowService;
import edu.neu.mdr.workflow.model.MdrProcess;

/**
 * Basic implmentation of the Administration interface using JEE features.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Stateless(name="Administration")
@WebService(portName = "AdministrationPort",
        serviceName = "AdministrationWebService",
        targetNamespace = "http://mdr.org/wsdl")
public class AdministrationServiceImpl implements AdministrationService {
	
	@PersistenceContext(unitName="mdr")
	EntityManager entityManager;
	
	@Resource 
	SessionContext context;
	
	@EJB(name="Workflow") 
	WorkflowService workflow;
	@EJB(name="Accreditorl") 
	AccreditorService accreditor;

	private static Logger logger = Logger
			.getLogger(AdministrationServiceImpl.class);

		
	@Override 	@RolesAllowed({"submitter","registrar","steward"})
	public long register(KeptItem admItem) throws RepositoryException {
		try {
			//System.out.println("USER:"+context.getCallerPrincipal().getName());

			String steward = "";
			String submitter = context.getCallerPrincipal().getName();
			String registrar = "";
			Contact submiterContact = accreditor.getContactInfo(submitter);
			admItem.setSubmitter(submiterContact);
			if (admItem.getHeader().getId()==0) {
				entityManager.persist(admItem.getHeader());
			}
			entityManager.persist(admItem);
			KeeperRole submiterRole = submiterContact.getRole();
			if (submiterRole==KeeperRole.submitter) {
				steward = accreditor.getSupervisor(submitter).getUsername();
				registrar = accreditor.getSupervisor(steward).getUsername();
			}
			else if  (submiterRole==KeeperRole.steward) {
				steward = submitter;
				registrar = accreditor.getSupervisor(steward).getUsername();
			}
			else if  (submiterRole==KeeperRole.registrar) {
				registrar = submitter;
				steward = submitter;
			}
			else {
				throw new RepositoryException("Only submitters registrars or stewards can submit items to the repository");
			}
			System.out.println("steward:"+steward);
			System.out.println("registrar:"+registrar);
			Procedure latestProcedure = workflow.getLatestActiveProcedure(steward);
			if (latestProcedure==null) {
				latestProcedure=workflow.getLatestActiveProcedure(registrar);
			}
			System.out.println("Using process "+latestProcedure);
			MdrProcess process = null;
			if (latestProcedure==null) {
				process = workflow.defaultProcess();
			}
			else {
				process = latestProcedure.getProcess();
			}
			System.out.println("Using process "+process.getName());
			workflow.createProcessInstance(process, admItem.getId(), submitter, steward, registrar);
			
			return admItem.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}
	
	@Override
	public void changeVisibility(String admItemIdStr,String newVisibility) throws RepositoryException {
		logger.info("** CHANGING VISIBILITY ***"); 
		long admItemId = Long.parseLong(admItemIdStr);
		try {
			// TODO: really we should make a copy of everything????
			KeptItem admItem = entityManager.find(KeptItem.class, admItemId);
			admItem.setVisibility(newVisibility);
			entityManager.persist(admItem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}
	
	
	@Override
	public void changeStatus(String admItemIdStr,String newStatus) throws RepositoryException {
		logger.info("** CHANGING STATE ***");
		long admItemId = Long.parseLong(admItemIdStr);
		try {
			// TODO: really we should make a copy of everything????
			KeptItem admItem = entityManager.find(KeptItem.class, admItemId);
			admItem.setStatus(newStatus);
			entityManager.persist(admItem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}


	@Override
	public KeptItem getKeptItem(long id) throws RepositoryException {
		return entityManager.find(KeptItem.class, id);
	}

	@Override
	public Long createKeptItemHeader(KeptItemHeader header) {
		entityManager.persist(header);
		return header.getId();
	}
	
	

	@PermitAll
	@Override
	public byte[] getModel(Long keptItemId) throws RepositoryException {
		String caller = context.getCallerPrincipal().getName();
		if (canSeeModel(keptItemId, caller)) {
			try {
				return IOUtils.toByteArray(new FileInputStream(getModelFile(keptItemId)));
			} catch (Exception e) {
				throw new RepositoryException(e);
			}
		}
		return null;
		
	}

	@Override
	public long registerAndUpload(KeptItem admItem, byte[] model)
			throws RepositoryException {
		try {
			long id = register(admItem);
			FileOutputStream fos = new FileOutputStream(getModelFile(id));
			IOUtils.write(model, fos);
			return id;
		} catch (FileNotFoundException e) {
			throw new RepositoryException(e);
		} catch (IOException e) {
			throw new RepositoryException(e);
		}
	}
	
	private File getModelFile(Long keptItem) {
		File dir = new File("./keptitems/"); // TODO: make this configurable
		if (!dir.exists()) 
			dir.mkdir();
		File modelFile = new File("./keptitems/"+keptItem+".model");
		return modelFile;
	}
	
	@Override
	public List<KeptItem> queryItems(String title,Date from,Date to,String status) throws RepositoryException {
		String user = context.getCallerPrincipal().getName();
		Query q = entityManager.createQuery("select k from KeptItem k where k.header.description like :title and k.modificationDate between :from and :to and k.status like :status ");
		q.setParameter("title", title);
		q.setParameter("from", from);
		q.setParameter("to", to);
		q.setParameter("status",status);
		List<KeptItem> ret = new ArrayList<KeptItem>();
		for (Object o:q.getResultList()) {
			KeptItem k = (KeptItem) o;
			if (canSeeModel(k.getId(), user))
				ret.add(k);
		}
		return ret;
	}
	
	
	/**
	 * Determines whether a user can see a model (by looking at the visibility)
	 * @param keptItem the kept item
	 * @param user the requesting user
	 * @return true iff the model is visible to the requesting user
	 * @throws RepositoryException
	 */
	private boolean canSeeModel(Long keptItem,String user) throws RepositoryException {
		if (user.equals("root")||user.equals("system"))
			return true;
		KeptItem item = getKeptItem(keptItem);
		if (item.getVisibility().equals("public"))
			return true;
		if (item.getSubmitter().getUsername().equals(user))
			return true;
		String supervisor = accreditor.getSupervisor(user).getUsername();
		while(!supervisor.equals("root")) {
			if (item.getSubmitter().getUsername().equals(supervisor))
				return true;
			supervisor = accreditor.getSupervisor(supervisor).getUsername();
		}
		return false;
		
	}
	
}
