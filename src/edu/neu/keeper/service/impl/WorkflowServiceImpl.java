package edu.neu.keeper.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXB;

import org.apache.log4j.Logger;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.KeeperRole;
import edu.neu.keeper.model.Procedure;
import edu.neu.keeper.model.Registrar;
import edu.neu.keeper.model.Steward;
import edu.neu.keeper.model.Submitter;
import edu.neu.keeper.model.TaskInstance;
import edu.neu.keeper.model.TaskInstanceList;
import edu.neu.keeper.model.User;
import edu.neu.keeper.model.VariableValue;
import edu.neu.keeper.service.AccreditorService;
import edu.neu.keeper.service.NotificationService;
import edu.neu.keeper.service.RepositoryAuthenticationException;
import edu.neu.keeper.service.RepositoryException;
import edu.neu.keeper.service.WorkflowService;
import edu.neu.mdr.workflow.model.ChangeVisibilityState;
import edu.neu.mdr.workflow.model.EndState;
import edu.neu.mdr.workflow.model.Field;
import edu.neu.mdr.workflow.model.FormTask;
import edu.neu.mdr.workflow.model.MdrProcess;
import edu.neu.mdr.workflow.model.State;
import edu.neu.mdr.workflow.model.Transition;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Basic implementation of the workflow interface using JEE features.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Stateless(name="Workflow")
@WebService(portName = "WorkflowPort",
        serviceName = "WorkflowWebService",
        targetNamespace = "http://mdr.org/wsdl")
@RolesAllowed("system")
public class WorkflowServiceImpl implements WorkflowService  {
	
	public static final String APPROVAL_PROCESS="approvalprocess";
	
	private static Logger logger = Logger.getLogger(WorkflowServiceImpl.class);
	
	@PersistenceContext(unitName="mdr")
	EntityManager entityManager;
	
	@Resource 
	SessionContext context;
	
	@EJB(name="Notification")
	NotificationService notificationService;
	
	@EJB(name="Accreditor")
	AccreditorService accreditorService;
	
	private ProcessEngine processEngine;
	
	private String convertToProcessDefinition(MdrProcess process) throws IOException, TemplateException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration();
		cfg.setTemplateLoader(new ClassTemplateLoader(WorkflowServiceImpl.class,"/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper()); 
		Template temp = cfg.getTemplate("process-template.xml");
		StringWriter out = new StringWriter();
		temp.process(process, out);
		out.flush(); 
		return out.toString().replaceAll("&", "&amp;");
	}
	
	private ProcessEngine getProcessEngine() {
		if (processEngine==null) {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// TODO: do this for real
		this.processEngine=processEngine;
		}
		return processEngine;
		
	}
	
	/* (non-Javadoc)
	 * @see edu.neu.mdr.workflow.service.WokflowService#createProcessDefinition(edu.neu.mdr.workflow.model.MdrProcess)
	 */
	@WebMethod(exclude=true)
	@RolesAllowed({"steward","registrar","accreditor","system"})
	public String createProcessDefinition(MdrProcess process) throws RepositoryException {
		try {
			saveMdrProcess(process);
			RepositoryService repositoryService = getProcessEngine().getRepositoryService();
			String xmlProcessDefinition = convertToProcessDefinition(process);
			System.out.println(xmlProcessDefinition);
			String deploymentId = repositoryService.createDeployment().addResourceFromString(process.getName()+".jpdl.xml",xmlProcessDefinition).deploy();//new File("src/"+process.getName()+".jpdl.xml")).deploy();;
			return deploymentId;
		} catch(Exception e) {
			throw new RepositoryException(e);
		}
	}
	
	//TODO: determine which steward
	/* (non-Javadoc)
	 * @see edu.neu.mdr.workflow.service.WokflowService#createProcessInstance(edu.neu.mdr.workflow.model.MdrProcess, long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@PermitAll
	@WebMethod(exclude=true)
	public void createProcessInstance(MdrProcess process,long keptItemId,String submitter,String steward,String registrar) {
		Map<String,String> vars = new HashMap<String,String>();
		vars.put("steward", steward);
		vars.put("submitter", submitter);
		vars.put("registrar", registrar);
		vars.put("admItem", ""+keptItemId);
		getProcessEngine().getExecutionService().startProcessInstanceByKey(process.getName(),vars);
	}
	
	/* (non-Javadoc)
	 * @see edu.neu.mdr.workflow.service.WokflowService#getUserForms(java.lang.String)
	 */
	@PermitAll
	@Override
	@WebMethod
	public TaskInstanceList getUserForms() throws RepositoryException {
		String username = context.getCallerPrincipal().getName();
		TaskService taskService = getProcessEngine().getTaskService();
		ExecutionService executionService = getProcessEngine().getExecutionService();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Map<Long,FormTask> ret = new HashMap<Long,FormTask>();
		List<Task> taskList = taskService.findPersonalTasks(username);
		for (Task task:taskList) {
			System.out.println("TASK:"+task);
			String state = task.getActivityName();
			String processInstanceId  = task.getExecutionId();
			ProcessInstance execution = executionService.findProcessInstanceById(processInstanceId);
			String processDefinitionId = execution.getProcessDefinitionId();
			ProcessDefinition process = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
			if (process.getKey().equals(APPROVAL_PROCESS)) continue; // we don't want the built in process here
			MdrProcess mdrProcess = getMdrProcess(process.getKey());
			if (mdrProcess==null) 
				throw new RepositoryException("Could not find any process definition with name "+process.getKey());
			FormTask formState = getFormTask(mdrProcess,state); 
			ret.put(Long.parseLong((String) executionService.getVariable(processInstanceId,"admItem")),formState);
		}
		ArrayList<TaskInstance> taskInstances = new ArrayList<TaskInstance>();
		for (Map.Entry<Long, FormTask> entry:ret.entrySet()) {
			taskInstances.add(new TaskInstance(entry.getKey(), entry.getValue()));
		}
		TaskInstanceList list = new TaskInstanceList();
		list.setTaskInstances(taskInstances);
		
		// for some reason the list would not return so roundtrip is needed with the following:
		// HACK:
		StringWriter sw = new StringWriter();
		JAXB.marshal(list, sw);
		list  = JAXB.unmarshal(new StringReader(sw.toString()), TaskInstanceList.class);
		// END HACK
		
		return list;
	}
	
	
	@Override
	@PermitAll
	public void submitFormTask(long keptItemId,List<VariableValue> varsValues) throws RepositoryException {
		String username = context.getCallerPrincipal().getName();
		TaskService taskService = getProcessEngine().getTaskService();
		ExecutionService executionService = getProcessEngine().getExecutionService();
		RepositoryService repositoryService = getProcessEngine().getRepositoryService();
		List<Task> taskList = taskService.findPersonalTasks(username);
		for (Task task:taskList) {
			String state = task.getActivityName();
			String processInstanceId  = task.getExecutionId();
			ProcessInstance execution = executionService.findProcessInstanceById(processInstanceId);
			String processDefinitionId = execution.getProcessDefinitionId();
			ProcessDefinition process = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
			MdrProcess mdrProcess = getMdrProcess(process.getKey()); 
			FormTask formState = getFormTask(mdrProcess,state);
			String admItemInProcess = (String) executionService.getVariable(processInstanceId,"admItem");
			if (admItemInProcess.equals(""+keptItemId)) {
				for (VariableValue val:varsValues) {
					if (formState.getFieldNames().contains(val.getVariableName())) {
							executionService.setVariable(processInstanceId, val.getVariableName(), val.getValue());
					}
				}
				taskService.completeTask(task.getId());
			}
		}
	}
	
	
	private FormTask getFormTask(MdrProcess mdrProcess, String state) {
		for (State s:mdrProcess.getStates()) {
			if (s instanceof FormTask && s.getName().equals(state)) {
				return (FormTask) s;
			}
		}
		return null;
	}

	private MdrProcess getMdrProcess(String id) throws RepositoryException {
		try {
			Query query = entityManager.createQuery("from MdrProcess where name=?");
			query.setParameter(1, id);
			MdrProcess process = (MdrProcess) query.getSingleResult();
			return process;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}
	
	
	private void saveMdrProcess(MdrProcess process) throws RepositoryException {
		try {
			for (State state:process.getStates()) {
				if (state.getTransitions()!=null) { 
					for(Transition transition:state.getTransitions()) {
						entityManager.persist(transition);
					}
				}
				if (state instanceof FormTask) {
					FormTask ft = (FormTask)state;
					for(Field f:ft.getFields()) {
						entityManager.persist(f);
					}
				}
				entityManager.persist(state);
			}
			entityManager.persist(process);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}
	

	@RolesAllowed({"steward","registrar"})
	@Override
	public Long createProcedure(Procedure procedure)
			throws RepositoryException {
		Steward s = entityManager.find(Steward.class, context.getCallerPrincipal().getName());
		procedure.setOwner(s);
		procedure.setApproved(false);
		MdrProcess process = entityManager.find(MdrProcess.class, procedure.getProcess().getName());
		if (process==null)
			createProcessDefinition(procedure.getProcess());
		entityManager.persist(procedure);		
		return procedure.getId();
	}
	

	@PermitAll
	@Override
	public Procedure getLatestActiveProcedure(String stewardOrRegistrar)
			throws RepositoryException {
		Query query = entityManager.createQuery("select max(id) from Procedure where owner.username=? and approved=true");
		query.setParameter(1, stewardOrRegistrar);
		Long maxId = (Long) query.getSingleResult();
		if (maxId==null) return null;
		return entityManager.find(Procedure.class, maxId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Procedure> getProcedures(String stewardOrRegistrar)
			throws RepositoryException {
		Query query = entityManager.createQuery("from Procedure where owner=?");
		query.setParameter(1, stewardOrRegistrar);
		ArrayList<Procedure> ret = new ArrayList<Procedure>();
		ret.addAll(query.getResultList());
		return ret;
	}
	
	@PermitAll
	@Override
	public MdrProcess defaultProcess() throws RepositoryException {
		Query query = entityManager.createQuery("from MdrProcess where name='defaultprocess'");
		
		MdrProcess ret = null;
		
		try {
			ret = (MdrProcess) query.getSingleResult();
		}
		catch (Exception e) {
			ret = new MdrProcess();
			ret.setName("defaultprocess");
			ChangeVisibilityState state = new ChangeVisibilityState();
			state.setName("makePublic");
			state.setNewVisibility("public");
			
			Transition toEnd = new Transition();
			toEnd.setToState("endState");
			toEnd.setTransitionName("toEnd");
			
			EndState endState = new EndState();
			endState.setName("endState");
			
			state.setTransitions(new ArrayList<Transition>());
			state.getTransitions().add(toEnd);
			
			ret.setStartState(state.getName());
			ret.setStates(new ArrayList<State>());
			ret.getStates().add(endState);
			ret.getStates().add(state);
			createProcessDefinition(ret);
		}
		
		return ret;
		
	}

	@RolesAllowed("registrar")
	@Override
	public void approveProcedure(Long procedureId) throws RepositoryException {
		Procedure p = entityManager.find(Procedure.class, procedureId);
		p.setApproved(true);
		entityManager.persist(p);
	}

	@RolesAllowed("registrar")
	@Override
	public void rejectProcedure(Long procedureId) throws RepositoryException {
		Procedure p = entityManager.find(Procedure.class, procedureId);
		p.setApproved(false);
		entityManager.persist(p);
		
	}
	
	private String serialize(Object obj) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(obj,sw);
		return sw.toString();
	}
	
	@SuppressWarnings({ })
	private <T> T deserialize(String s,Class<T> type) {
		StringReader sr = new StringReader(s);
		return JAXB.unmarshal(sr, type);
	}
	
	@Override
	@PermitAll
	public String requestAccreditActor(Contact newActor,String supervisorUsername) throws RepositoryException {
		//Contact supervisor = entityManager.find(Contact.class,supervisorUsername);
		Map<String,String> vars = new HashMap<String,String>();
		vars.put("role", newActor.getRole().toString());
		if (newActor.getRole()==KeeperRole.accreditor) throw new RepositoryAuthenticationException("Accreditors cannot be registered this way");
		// TODO: add a check here to make sure that if the new actor is a submitter, the supervisor is a steward, and so on.
		vars.put("supervisor", supervisorUsername);
		vars.put("requestor", newActor.getUsername());
		vars.put("requestorObject", serialize(newActor));
		
		vars.put("status","pending");
		String ticket = getProcessEngine().getExecutionService().startProcessInstanceByKey(APPROVAL_PROCESS,vars).getId();
		notificationService.notifyMessage(supervisorUsername, "A new request by user "+newActor.getContact_name()+ " has been place under ticket number "+
					ticket+". This decision has been added to your queue.");
		notificationService.notifyByEmail(newActor.getEmail(), newActor.getEmail(), "A new request has been placed to user "+supervisorUsername+ " under ticket id "
				+ticket+". You will receive a notification with the status of your request, upon its confirmation");
		getProcessEngine().getExecutionService().setVariable(ticket, "ticket", ticket);
		return ticket;
	}
	
	@Override
	@PermitAll
	public String requestAccreditRegistrar(Registrar registrar) throws RepositoryException {
		return requestAccreditActor(registrar, "root");
	}
	
	
	@Override
	@PermitAll
	public String inquireActorRegistrationStatus(String ticket) {
		// TODO: check for security?
		ExecutionService executionService = getProcessEngine().getExecutionService();
		String status  = (String) executionService.getVariable(ticket,"status");
		return status;
	}
	
	@Override
	@PermitAll
	public List<String> getPendingActorRegistrationDecisions() {
		String username = context.getCallerPrincipal().getName();
		TaskService taskService = getProcessEngine().getTaskService();
		List<String> ret = new ArrayList<String>();
		List<Task> taskList = taskService.findPersonalTasks(username);
		for (Task task:taskList) {
			String processInstanceId  = task.getExecutionId();
			ret.add(processInstanceId);
		}
		return ret;
	}
	
	@Override
	@RolesAllowed({"steward","registrar","submitter"})
	public String getRequestDetails(String ticket) {
		ExecutionService executionService = getProcessEngine().getExecutionService();
		return (String) executionService.getVariable(ticket,"requestorObject");
	}
	
	
	
	
	private void approveActorRegistration(String ticket,String newStatus) throws RepositoryAuthenticationException{
		System.out.println("Approving:"+ticket);
		String username = context.getCallerPrincipal().getName();
		TaskService taskService = getProcessEngine().getTaskService();
		List<Task> taskList = taskService.findPersonalTasks(username);
		for (Task task:taskList) {
			String processInstanceId  = task.getExecutionId();
			if (processInstanceId.equals(ticket)) {
				getProcessEngine().getExecutionService().setVariable(processInstanceId, "status", newStatus);
				taskService.completeTask(task.getId());
				return;
			}
		}
		throw new RepositoryAuthenticationException("You do not have approval rights over this ticket");
	}
	
	@RolesAllowed({"steward","registrar","submitter","root","system"})
	@Override
	public void approveActorRequest(String ticket) throws RepositoryAuthenticationException {
		approveActorRegistration(ticket, "approved");
	}
	
	@PermitAll
	public void denyActorRegistration(String ticket) throws RepositoryException {
		ExecutionService executionService = getProcessEngine().getExecutionService();
		User contact = deserialize((String) executionService.getVariable(ticket,"requestorObject"),User.class);
		notificationService.notifyByEmail(contact.getUsername(), contact.getEmail(), "Your request number "+ticket+" to become a submitter has been denied. Please contact the supervisor for details.");
	}
	
	@RolesAllowed({"steward","registrar","submitter","root","system"})
	@Override
	public void denyActorRequest(String ticket) throws RepositoryAuthenticationException {
		approveActorRegistration(ticket, "denied");
	}
	
	@PermitAll
	@Override
	public void approveActorRegistration(String ticket) throws RepositoryException {
		try{
			ExecutionService executionService = getProcessEngine().getExecutionService();
		
			String supervisor = (String) executionService.getVariable(ticket,"supervisor");
			String role = (String) executionService.getVariable(ticket,"role");
			String tempPassword = generateRandomPassword();
			if (role.equals("registrar")) {
				Registrar contact = deserialize((String) executionService.getVariable(ticket,"requestorObject"),Registrar.class);
				Contact supervisorContact  = new Contact();
				supervisorContact.setUsername(supervisor);
				contact.setCreator(supervisorContact);
				accreditorService.createRegistrar(contact, tempPassword);
				String username = contact.getUsername();
				notificationService.notifyMessage(username, "Your request ticket  "+ticket+" to become a registrar has been approved. Your username is "+username+
						" and your temporary password is "+tempPassword);
			}
			else if (role.equals("steward")) {
				Steward contact = deserialize((String) executionService.getVariable(ticket,"requestorObject"),Steward.class);
				Contact supervisorContact  = new Contact();
				supervisorContact.setUsername(supervisor);
				contact.setCreator(supervisorContact);
				accreditorService.createSteward(contact, tempPassword);
				String username = contact.getUsername();
				notificationService.notifyMessage(username, "Your request number "+ticket+" to become a steward has been approved. Your username is "+username+
						" and your password is "+tempPassword);
			}
			else if (role.equals("submitter")) {
				Submitter contact = deserialize((String) executionService.getVariable(ticket,"requestorObject"),Submitter.class);
				Contact supervisorContact  = new Contact();
				supervisorContact.setUsername(supervisor);
				contact.setCreator(supervisorContact);
				accreditorService.createSubmitter(contact, tempPassword);
				String username = contact.getUsername();
				notificationService.notifyMessage(username, "Your request number "+ticket+" to become a submitter has been approved. Your username is "+username+
						" and your password is "+tempPassword);
			}
			else if (role.equals("user")) {
				User contact = deserialize((String) executionService.getVariable(ticket,"requestorObject"),User.class);
				Contact supervisorContact  = new Contact();
				supervisorContact.setUsername(supervisor);
				contact.setCreator(supervisorContact);
				accreditorService.createReadOnlyUser(contact, tempPassword);
				String username = contact.getUsername();
				notificationService.notifyMessage(username, "Your request number "+ticket+" to become a read-only user has been approved. Your username is "+username+
						" and your password is "+tempPassword);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}
	
	private String generateRandomPassword() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int x = 65+(int)(Math.random()*24);
			sb.append((char)x);
		}
		return sb.toString();
	}

	
	
	
	@WebMethod(exclude=true)
	@Override
	public void createApprovalProcessDefinition() {
		RepositoryService repositoryService = getProcessEngine().getRepositoryService();
		String procesDefFile = "/"+APPROVAL_PROCESS+".jpdl.xml";
		InputStream is = WorkflowServiceImpl.class.getResourceAsStream(procesDefFile);
		repositoryService.createDeployment().addResourceFromInputStream(APPROVAL_PROCESS+".jpdl.xml", is).deploy();
	}
	
	
}

enum RequestType {
	registrar,steward,submitter,user;
}


