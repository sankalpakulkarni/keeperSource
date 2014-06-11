package edu.neu.keeper.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import edu.neu.keeper.model.KeptItem;
import edu.neu.keeper.model.KeptItemHeader;

/**
 * The administration service provides methods for the registration and modification of 
 * administrative items in the registry.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 *
 */
@WebService(targetNamespace="http://mdr.org/wsdl" )
public interface AdministrationService {
	/**
	 * Creates a new kept item header in the system
	 * @param header
	 * @return the id of the newly created kept item header
	 */
	public Long createKeptItemHeader(KeptItemHeader header);
	
	/**
	 * Registers an item and submits it to the keeper controlled workflow
	 * @param admItem the kept item
	 * @return the kept item id
	 * @throws RepositoryException
	 */
	public long register(KeptItem admItem) throws RepositoryException;
	
	
	/**
	 * Registers an item and submits it to the keeper controlled workflow
	 * @param admItem the kept item
	 * @param the stream with the model to upload
	 * @return the kept item id
	 * @throws RepositoryException
	 */
	public long registerAndUpload(KeptItem admItem,byte[] model) throws RepositoryException;
	/**
	 * Reads a model from the repository and returns it in the model output stream.
	 * @param model
	 * @throws RepositoryException
	 */
	public byte[] getModel(Long keptItemId) throws RepositoryException;

	/**
	 * @param id the kept item id
	 * @return the kept item object
	 * @throws RepositoryException
	 */
	public KeptItem getKeptItem(long id) throws RepositoryException;
	
	/**
	 * Modifies the status of a kept item
	 * @param admItemIdStr the kept item id
	 * @param newStatus the new status
	 * @throws RepositoryException
	 */
	void changeStatus(String admItemIdStr, String newStatus)
			throws RepositoryException;

	/**
	 * Changes the visibility of an item 
	 * @param admItemIdStr the kept item id
	 * @param newVisibility the new visibility
	 * @throws RepositoryException
	 */
	void changeVisibility(String admItemIdStr, String newVisibility)
			throws RepositoryException;

	/**
	 * Returns all kept items given a criteria
	 * @param title the header description (use % as wildcard)
	 * @param from the from date for the modification date of the item
	 * @param to the to date fot the modification date of the item
	 * @param status the status of the item (use % as wildcard)
	 * @return a list of kept items that are visible to the calling user
	 * @throws RepositoryException
	 */
	List<KeptItem> queryItems(String title, Date from, Date to, String status)
			throws RepositoryException;
}
