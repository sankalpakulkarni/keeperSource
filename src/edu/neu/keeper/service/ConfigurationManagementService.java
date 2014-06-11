package edu.neu.keeper.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface ConfigurationManagementService {
	
	/**
	 * Creates a model given the uri
	 * @param uri the uri
	 * @param model the model
	 * @return the version of the committed model
	 * @throws VersioningException
	 */
	public String createModel(String uri,OutputStream model) throws VersioningException;
	
	/**
	 * Updates a model
	 * @param uri the uri
	 * @param model the model stream
	 * @return the new version of the model
	 * @throws VersioningException
	 */
	public String commitModel(String uri,OutputStream model) throws VersioningException;
	/**
	 * reads the model from the repository backend
	 * @param uri the uri
	 * @param version the version
	 * @param modelInputStream the stream to read the model
	 */
	public void getModel(String uri,String version, InputStream modelInputStream);
	
	/**
	 * reads the latest version of a  model from the repository backend
	 * @param uri the uri
	 * @param version the version
	 * @param modelInputStream the stream to read the model
	 */
	public void getModel(String uri, InputStream modelInputStream);
	
	/**
	 * Deletes a model from the system
	 * @param uri the uri of the model to delete
	 * @return the version of the uri, with the 
	 */
	public String deleteModel(String uri);
		
	
}
