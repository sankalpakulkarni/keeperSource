package edu.neu.keeper.service;
/**
 * This is a service to allow for the creation of a default accreditor,
 * the first time the system runs.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public interface BootstrapService {
	public void createDefaultAccreditor() throws RepositoryException;
	public void createSystemUser() throws RepositoryException;
}
