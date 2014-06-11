package edu.neu.keeper.service;


import javax.jws.WebService;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.model.Registrar;
import edu.neu.keeper.model.Steward;
import edu.neu.keeper.model.Submitter;
import edu.neu.keeper.model.User;

/**
 * Prototype implementation for the Accreditor service. 
 * 
 * This interface will deal with those use cases involved in creating and modifying the various
 * actors in the MDR.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@WebService(targetNamespace="http://mdr.org/wsdl" )
public interface AccreditorService {
	
	public void createRegistrar(Registrar registrar,String tempPassword) throws RepositoryException;
	/**
	 * Creates a new steward in the system
	 * @param contact the new steward 
	 * @param tempPassword a temporary password
	 * @throws RepositoryException
	 */
	public void createSteward(Steward contact,String tempPassword) throws RepositoryException;
	/**
	 * Creates a new submitter in the system
	 * @param contact the steward
	 * @param tempPassword a temporary password
	 * @throws RepositoryException
	 */
	public void createSubmitter(Submitter contact,String tempPassword) throws RepositoryException;
	/**
	 * Creates a read only user
	 * @param contact the user object
	 * @param tempPassword a temporary password
	 * @throws RepositoryException
	 */
	public void createReadOnlyUser(User contact,String tempPassword) throws RepositoryException;
	/**
	 * Updates the password of the calling user
	 * @param newPassword
	 */
	public void updatePassword(String newPassword);
	

	/**
	 * Authenticates a user in the system.
	 * @param username
	 * @param password
	 * @return the contact information of the authentiated user
	 * @throws RepositoryAuthenticationException
	 */
	public Contact authenticate(String username,String password) throws RepositoryAuthenticationException;
	/**
	 * Returns the supervisor of a user (ie. if the user is a steward, it return 
	 * the registrar that created the steward.
	 * @param username
	 * @return the contact information of the supervisor
	 * @throws RepositoryException
	 */
	public Contact getSupervisor(String username) throws RepositoryException;
	/**
	 * Gets the contact information of a user.
	 * @param username the user
	 * @return the contact object for the given username
	 * @throws RepositoryException
	 */
	public Contact getContactInfo(String username) throws RepositoryException;
	/**
	 * Updates the data of a contact. This can only be done either by the system user or
	 * the calling user. 
	 * @param user the user subject to the password change
	 * @param newPassword the new password for the user
	 * @throws RepositoryException
	 */
	public void updatePasswordForUser(String user, String newPassword) throws RepositoryException;
	
	/**
	 * Updates the contact info for a username
	 * @param username the user
	 * @param contactInfo the new contact info
	 * @param contactName the new contact name
	 * @param contactTitle the new contact title
	 * @param email the new email
	 * @throws RepositoryException
	 */
	public void updateContactInfo(String username, String contactInfo,
			String contactName, String contactTitle, String email)
			throws RepositoryException;
	
}
