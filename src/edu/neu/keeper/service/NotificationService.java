package edu.neu.keeper.service;

import java.util.List;

/**
 * A Generic Notification Service
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public interface NotificationService {
	/**
	 * Notifies a message to a user 
	 * @param username the user
	 * @param text the message
	 * @throws RepositoryException
	 */
	public void notifyMessage(String username,String text) throws RepositoryException;
	/**
	 * In some cases, objects need to be sent to the user as well as text. These are serialized and 
	 * appended to the message. 
	 * 
	 * @param username the username
	 * @param text the message
	 * @param objects the objects to be serialized and sent
	 * @throws RepositoryException
	 */
	public void notifyMessage(String username,String text,List<Object> objects) throws RepositoryException;
	/**
	 * Notifies a messag toa user (that may not be in the database yet) 
	 * @param user the user's name
	 * @param email the email address to which the message should be sent
	 * @param text the text of the message
	 * @throws RepositoryException
	 */
	public void notifyByEmail(String user, String email, String text) throws RepositoryException;
	
}
 