package edu.neu.keeper.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.Procedure;
import edu.neu.keeper.model.Registrar;
import edu.neu.keeper.model.TaskInstanceList;
import edu.neu.keeper.model.VariableValue;
import edu.neu.mdr.workflow.model.MdrProcess;
/**
 * This interface allows users to create new process definitions, start a process instance,
 * query the tasks that a user needs to complete and submit the information needed.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@WebService(targetNamespace="http://mdr.org/wsdl" )
public interface WorkflowService {

	/**
	 * Internal method (not exposed as webservice) to create a process definition
	 * @param process
	 * @throws RepositoryException
	 */
	@WebMethod(exclude=true)
	public String createProcessDefinition(MdrProcess process)
			throws RepositoryException;

	//TODO: determine which steward
	/**
	 * Internal method (not exposed as webservice) to create a process instance
	 * @param process
	 * @throws RepositoryException
	 */
	@WebMethod(exclude=true)
	public void createProcessInstance(MdrProcess process,
			long keptItemId, String submitter, String steward,
			String registrar);

	/**
	 * Returns the current form tasks that a user needs to complete for the different
	 * active workflows.The user is the calling user.
	 * @return
	 * @throws RepositoryException
	 */
	public TaskInstanceList getUserForms() throws RepositoryException;

	public void submitFormTask(
			long keptItemId,List<VariableValue> varsValues) throws RepositoryException;

	
	public List<Procedure> getProcedures(String stewardOrRegistrar)
	throws RepositoryException;
	/**
	 * Creates a procedure for the calling steward or registrar.
	 * @param procedure the new procedure
	 * @return the procedure id
	 * NOTE: This method will replace the procedure.owner property with the calling user AND
	 * a created procedure will always be NOT approved by default regardles of the value submitted.
	 * Use approveProcedure
	 * @throws RepositoryException
	 */
	public Long createProcedure(Procedure procedure)
	throws RepositoryException;
	/**
	 * Approves a procedure
	 * @param procedureId
	 * @throws RepositoryException
	 */
	public void approveProcedure(Long procedureId) 	throws RepositoryException;
	/**
	 * Rejects a procedure (i.e. changes to not approved)
	 * @param procedureId the procedure id
	 * @throws RepositoryException
	 */
	public void rejectProcedure(Long procedureId) 	throws RepositoryException;
	
	/**
	 * Retrieves the latest active procedure for a particular steward or registrar
	 * @param stewardOrRegistrar the steward or registrar
	 * @return the latest procedure, or the default procedure if one does not exist
	 * @throws RepositoryException
	 */
	public Procedure getLatestActiveProcedure(String stewardOrRegistrar) 
	throws RepositoryException;

	/**
	 * @return Returns the default process that makes an item automatically publicly visible
	 * @throws RepositoryException
	 */
	public MdrProcess defaultProcess() throws RepositoryException;

	/**
	 * A user request to become a registrar
	 * @param registrar the registrar object (must include the registration authority)
	 * @return the ticket id to check for the status of the approval
	 * @throws RepositoryException
	 */
	public String requestAccreditRegistrar(Registrar registrar)
			throws RepositoryException;

	/**
	 *  A user request to become a steward, submitter or read only user.
	 * @param newActor the new contact 
	 * @param supervisorUsername the name of the supervisor to which the request will be placed
	 * @return the ticket id
	 * @throws RepositoryException
	 */
	public String requestAccreditActor(Contact newActor, String supervisorUsername)
			throws RepositoryException;
	
	/**
	 * Checks for the status an actor registartion approval request
	 * @param ticket the ticket generated upon the initial request
	 * @return the status of the given ticket
	 */
	public String inquireActorRegistrationStatus(String ticket);

	/**
	 * @return the list of ticket ids for which the calling actor has pending aproval decisions
	 */
	public List<String> getPendingActorRegistrationDecisions();
	/**
	 * Gets the information of the user requesting to become a user
	 * @param ticket the ticket generated upon the initial request
	 * @return the serialized object of the requestor user
	 */
	public String getRequestDetails(String ticket);
	
	/**
	 * This method is only called by the workflow EJB.
	 * Approves the registration of a particular actor in the system.
	 * @param ticket the ticket number
	 * @throws RepositoryException
	 */
	@WebMethod(exclude=true)
	void approveActorRegistration(String ticket) throws RepositoryException;
	/**
	 * This method is only called by the workflow as EJB
	 * Denies the request of a user to become a user in the system.
	 * @param ticket the ticket generated upon the actor registration approval request
	 * @throws RepositoryException
	 */
	@WebMethod(exclude=true)
	void denyActorRegistration(String ticket) throws RepositoryException;

	void createApprovalProcessDefinition();
	/**
	 * Approves a user request.
	 * 
	 * @param ticket The ticket id.
	 * @throws RepositoryAuthenticationException
	 */
	void denyActorRequest(String ticket) throws RepositoryAuthenticationException;

	/**
	 * Denies a user request.
	 * 
	 * @param ticket the ticket id
	 * @throws RepositoryAuthenticationException
	 */
	public void approveActorRequest(String ticket) throws RepositoryAuthenticationException;
}