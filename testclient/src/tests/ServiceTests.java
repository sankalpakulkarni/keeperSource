package tests;

import java.util.ArrayList;
import java.util.List;

import org.mdr.wsdl.AccreditorService;
import org.mdr.wsdl.Contact;
import org.mdr.wsdl.LanguageIdentification;
import org.mdr.wsdl.Registrar;
import org.mdr.wsdl.RegistrationAuthority;
import org.mdr.wsdl.RegistrationAuthorityIdentifier;
import org.mdr.wsdl.WorkflowService;




/** 
 * A minimal call to the web service. Showing how to create a registrar.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class ServiceTests {
	public static void main(String[] args) throws Exception {
		AccreditorService accService = WebServiceUtils.getAccreditorService("root", "root");
		try{
		RegistrationAuthority ra = createRegistrationAuthority();
		Registrar r = new Registrar();
		r.setContactName("New Pinto");
		r.setContactInformation(" - ");
		r.setRegistrationAuthority(ra);
		r.setUsername("New");
		
		accService.createRegistrar(r, "New");
		System.out.println("Completed");
//			Contact con =  accService.getContactInfo("John");
//			System.out.println(con.getContactName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
//		edu.neu.keeper.model.Contact c = new Contact();
//		c.setContact_name("Steward");
//		c.setContact_information("-");
//		c.setPassword("test123");
//		c.setUsername("test")
//		accService.createSteward(c, "test123");
//		Contact s = accService.getSupervisor("test");
//		System.out.println(s.getContactInformation());
		
//		WorkflowService service = WebServiceUtils.getWorkflowService("root", "root");
//		System.out.println(service.);
	}
	
	

	/**
	 * creates a sample registration authority
	 * @return
	 */
	public static RegistrationAuthority createRegistrationAuthority() {
		LanguageIdentification langid = new LanguageIdentification();
		langid.setCountryIdentifier("UY");
		langid.setLanguageIdentifier("Spanish");
		
		RegistrationAuthority registrationAuthority = new RegistrationAuthority();
		// registration authority needs a lang identifier list
		List<LanguageIdentification> documentationLanguageIdentifier = new ArrayList<LanguageIdentification>();
		documentationLanguageIdentifier.add(langid);
		// set the lang identifier list
		registrationAuthority.getDocumentationLanguageIdentifier().add(langid);
		
		registrationAuthority.setOrganizationMailAddress("sample@ccs.neu.edu");
		registrationAuthority.setOrganizationName("Northeastern University");
		// a registration authority needs a registration authority identifier
		RegistrationAuthorityIdentifier registrationAuthorityIdentifier;
		registrationAuthorityIdentifier= new RegistrationAuthorityIdentifier();
		registrationAuthorityIdentifier.setInternationalCodeDesignator("NEU");
		registrationAuthorityIdentifier.setOPISource("NEU_NamingDepartment");
		registrationAuthorityIdentifier.setOrganizationIdentifier("neu.edu");
		registrationAuthorityIdentifier.setOrganizationPartIdentifier("OntologyDepartment");
		registrationAuthority.setRegistrationAuthorityIdentifier(registrationAuthorityIdentifier);
		return registrationAuthority;
	}
}
