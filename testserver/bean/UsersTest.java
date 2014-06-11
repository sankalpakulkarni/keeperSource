package bean;

import static org.junit.Assert.assertTrue;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.neu.keeper.service.AccreditorService;
import edu.neu.keeper.service.BootstrapService;
/**
 * This class will test use cases that relate to the creation of users and their permissions.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class UsersTest {

	private InitialContext initialContext;

	@Before
	public void setUp() throws Exception {
		System.setProperty("java.security.auth.login.config", this.getClass()
				.getResource("/login.conf").toExternalForm());
		initialContext = new InitialContext();
		
		BootstrapService bootstrapService = (BootstrapService) initialContext
				.lookup("BootstrapLocal");
		bootstrapService.createDefaultAccreditor(); // root,root is the default
		bootstrapService.createSystemUser();
	}

	@After
	public void shutdown() throws NamingException {
		initialContext.close();
	}

	@Test
	public void testAuthenticateUser() throws Exception {
		AccreditorService acrWs = WebServiceUtils.getAccreditorService("root","root");
		acrWs.authenticate("root", "root");
		
		try {
			acrWs = WebServiceUtils.getAccreditorService("root","root");
			acrWs.authenticate("root", "wrongpassword");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("Wrong credentials"));
		}
		
		try {
			acrWs = WebServiceUtils.getAccreditorService("root","wrongpassword");
			acrWs.authenticate("root", "wrongpassword");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("wrong password"));
		}
	}

}
