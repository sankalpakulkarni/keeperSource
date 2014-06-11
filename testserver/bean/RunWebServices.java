package bean;

import javax.naming.InitialContext;

import edu.neu.keeper.service.BootstrapService;
/**
 * Starts the web services in standalone mode.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class RunWebServices {
	public static void main(String[] args) throws Exception {
		System.setProperty("java.security.auth.login.config", RunWebServices.class
				.getResource("/login.conf").toExternalForm());
		InitialContext initialContext = new InitialContext();
		BootstrapService bootstrapService = (BootstrapService) initialContext
				.lookup("BootstrapLocal");
		bootstrapService.createDefaultAccreditor(); // root,root is the default
		bootstrapService.createSystemUser();
		
		while (true) {
			Thread.sleep(5000);
		}
		
	}
}
