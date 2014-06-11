package bean;

import org.apache.openejb.core.security.SecurityServiceImpl;
/**
 * SecurityService that defaults to the HQLModule as a security realm.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class HQLServiceProvider extends SecurityServiceImpl {

	public HQLServiceProvider() {
		super();
		this.setRealmName("HQLModule");
	}
	
	@Override
	public String getRealmName() {
		return "HQLModule";
	}

	
	
}
