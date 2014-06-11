package edu.neu.keeper.service;
/**
 * An exception occurring when the user accessing the repository is not properly
 * authenticated.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class RepositoryAuthenticationException extends RepositoryException {

	private static final long serialVersionUID = 1L;

	public RepositoryAuthenticationException() {
		super();
	}

	public RepositoryAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryAuthenticationException(String message) {
		super(message);
	}

	public RepositoryAuthenticationException(Throwable cause) {
		super(cause);
	}

	

}
