package bean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.neu.keeper.model.KeptItem;
import edu.neu.keeper.model.KeptItemHeader;
import edu.neu.keeper.model.LanguageIdentification;
import edu.neu.keeper.model.Procedure;
import edu.neu.keeper.model.Registrar;
import edu.neu.keeper.model.RegistrationAuthority;
import edu.neu.keeper.model.RegistrationAuthorityIdentifier;
import edu.neu.keeper.model.Steward;
import edu.neu.keeper.model.Submitter;
import edu.neu.keeper.model.TaskInstance;
import edu.neu.keeper.model.VariableValue;
import edu.neu.keeper.service.AccreditorService;
import edu.neu.keeper.service.AdministrationService;
import edu.neu.keeper.service.BootstrapService;
import edu.neu.keeper.service.WorkflowService;
import edu.neu.mdr.workflow.model.ConditionTransition;
import edu.neu.mdr.workflow.model.DecisionState;
import edu.neu.mdr.workflow.model.EndState;
import edu.neu.mdr.workflow.model.Field;
import edu.neu.mdr.workflow.model.FormTask;
import edu.neu.mdr.workflow.model.MdrProcess;
import edu.neu.mdr.workflow.model.ModifyStatusState;
import edu.neu.mdr.workflow.model.State;
import edu.neu.mdr.workflow.model.Transition;

/**
 * This is a sample client that demonstrates the prototype implementation
 * of the workflow.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class WorkflowTest {
	
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
		
		WorkflowService wk = (WorkflowService) initialContext.lookup("WorkflowLocal");
		wk.createApprovalProcessDefinition();
		
	}

	@After
	public void shutdown() throws NamingException {
		initialContext.close();
	}
	
	public Date getDate(int month,int day,int year) {
		GregorianCalendar g = new GregorianCalendar();
		g.set(Calendar.MONTH, month);
		g.set(Calendar.DAY_OF_MONTH, day);
		g.set(Calendar.YEAR, year);
		return g.getTime();
	}

	
	/**
	 * Creates a sample mdr process
	 */
	public  MdrProcess createMdrProcess() {
		MdrProcess process = new MdrProcess();
		DecisionState decision = new DecisionState();
		FormTask form = new FormTask();
		Field name = new Field();
		State endState = new EndState();
		ModifyStatusState modState = new ModifyStatusState();
		List<Transition> transitionsForm = new ArrayList<Transition>();
		List<Transition> transitionsDecision = new ArrayList<Transition>();
		List<Field> fields = new ArrayList<Field>();
		Transition toDecision = new Transition();
		ConditionTransition toEnd = new ConditionTransition();
		ConditionTransition toMod = new ConditionTransition();
		
		
		process.setName("sample_process");
		process.setStates(new ArrayList<State>());
		process.getStates().add(form);
		process.getStates().add(decision);
		process.getStates().add(modState);
		process.getStates().add(endState);
		
		
		form.setName("Fill this form");
		form.setFields(fields);
		form.setTransitions(transitionsForm);
		form.setAsignee("steward");
		transitionsForm.add(toDecision);
		toDecision.setToState("Decide");
		
		
		fields.add(name);
	
		name.setName("someVar");
		name.setLength(10);
		name.setType("java.lang.String");
		
		decision.setName("Decide");
		decision.setTransitions(transitionsDecision);
		transitionsDecision.add(toEnd);
		transitionsDecision.add(toMod);
		
		
		toEnd.setTransitionName("toEnd");
		toEnd.setToState(endState.getName());
		
		toMod.setTransitionName("toMod");
		toMod.setToState("mod");
		toMod.setCondition("#{someVar=='x'}");
		
		endState.setName("endState");
		
		toDecision.setTransitionName("toDecision");
		toEnd.setCondition("#{someVar=='y'}");
		toEnd.setTransitionName("toEnd");
		toEnd.setToState(endState.getName());
		
		modState.setName("mod");
		modState.setNewStatus("standard");
		List<Transition> toEndTranistion = new ArrayList<Transition>();
		Transition toEnd2 = new Transition();
		toEnd2.setToState("endState");
		toEnd2.setTransitionName("toEnd");
		toEndTranistion.add(toEnd2);
		modState.setTransitions(toEndTranistion);
		
		process.setStartState(form.getName());

		
		return process;
	}
	
	/**
	 * creates a sample kept item 
	 * @param registrationAuthority
	 * @return
	 * @throws ParseException
	 */
	public KeptItem createKeptItem() throws ParseException {
		
		KeptItemHeader header = new KeptItemHeader();
		header.setDescription("Ket header");
		
		KeptItem termsAdmRecord = new KeptItem();
		termsAdmRecord.setStatus("pending");
		termsAdmRecord.setModificationDate(getDate(10,10,2010));
		termsAdmRecord.setChangeDescription("Use at will");
		termsAdmRecord.setHeader(header);
		
		return termsAdmRecord;
	}
	
	/**
	 * creates a sample registration authority
	 * @return
	 */
	public RegistrationAuthority createRegistrationAuthority() {
		LanguageIdentification langid = new LanguageIdentification();
		langid.setCountry_identifier("UY");
		langid.setLanguage_identifier("Spanish");
		
		//acService.createLanguageIdentification(langid);
		
		RegistrationAuthority registrationAuthority = new RegistrationAuthority();
		// registration authority needs a lang identifier list
		List<LanguageIdentification> documentationLanguageIdentifier = new ArrayList<LanguageIdentification>();
		documentationLanguageIdentifier.add(langid);
		// set the lang identifier list
		registrationAuthority.setDocumentation_language_identifier(documentationLanguageIdentifier);
		
		registrationAuthority.setOrganization_mail_address("sample@ccs.neu.edu");
		registrationAuthority.setOrganization_name("Northeastern University");
		// a registration authority needs a registration authority identifier
		RegistrationAuthorityIdentifier registrationAuthorityIdentifier;
		registrationAuthorityIdentifier= new RegistrationAuthorityIdentifier();
		registrationAuthorityIdentifier.setInternational_code_designator("NEU");
		registrationAuthorityIdentifier.setOPI_source("NEU_NamingDepartment");
		registrationAuthorityIdentifier.setOrganization_identifier("neu.edu");
		registrationAuthorityIdentifier.setOrganization_part_identifier("OntologyDepartment");
		registrationAuthority.setRegistration_authority_identifier(registrationAuthorityIdentifier);
		return registrationAuthority;
	}
	
	
	
	
	/**
	 * Program that:
	 * 1.creates and stores a registration authority and a kept item.
	 * 2. Defines a workflow into the system and stores it.
	 * 3. Starts a workflow instance with form completion and change of status
	 * this mini example shows that the status changes from pending to standard
	 * after a run by the workflow.
	 * @param args
	 * @throws Exception
	 */
	
	@Test
	public void testWorkflow() throws Exception{
		
		// Create Some Users
		
		AccreditorService accService = WebServiceUtils.getAccreditorService("root", "root");
		
		RegistrationAuthority ra = createRegistrationAuthority();
				
		Registrar r = new Registrar();
		r.setContact_name("John Hancock");
		r.setContact_information(" - ");
		r.setRegistrationAuthority(ra);
		r.setUsername("john");
		
		accService.createRegistrar(r, "john");
		
		AccreditorService accServiceJohn = WebServiceUtils.getAccreditorService("john", "john");
		
		Steward s = new Steward();
		s.setContact_information(" - ");
		s.setContact_name("Sam Adams");
		s.setUsername("sam");
		
		accServiceJohn.createSteward(s, "sam");
		
		AccreditorService accServiceSam = WebServiceUtils.getAccreditorService("sam", "sam");
		
		Submitter m = new Submitter();
		m.setContact_name("Thomas Menino");
		m.setContact_information(" - ");
		m.setUsername("thomas");
		
		accServiceSam.createSubmitter(m, "thomas");
		
		// Instantiate the services. This will be really web service endpoints
		AdministrationService adminService = WebServiceUtils.getAdministrationService("thomas", "thomas");
		WorkflowService workflowService =  WebServiceUtils.getWorkflowService("sam", "sam");
		
		// create a sample administative item with default process
		KeptItem admItem = createKeptItem();
		long id = adminService.register(admItem);
		admItem = adminService.getKeptItem(id);
		assertEquals(admItem.getVisibility(),"public");
		
		//Now create a sample procedure
		Procedure proc = new Procedure();
		proc.setDescrpition("Sample Procedure");
		// create a process definition
		MdrProcess process = JAXB.unmarshal(WorkflowTest.class.getResourceAsStream("/sample-process.xml"),MdrProcess.class);//createMdrProcess();
		proc.setProcess(process);
		Long procedureId = workflowService.createProcedure(proc);
		
		//the registrar approved the procedure
		WorkflowService workflowServiceReg =  WebServiceUtils.getWorkflowService("john", "john");
		workflowServiceReg.approveProcedure(procedureId);
		
		// let an administrative item run through the process
		admItem = createKeptItem();
		
		// initial status should be pending
		System.out.println("Current status is "+admItem.getStatus());
		assertEquals("pending",admItem.getStatus());
		
		long idItem = adminService.register(admItem);

		List<TaskInstance> tasks =  workflowService.getUserForms().getTaskInstances();
		assertEquals(tasks.size(), 1); // there should only be one pending task for this user
		TaskInstance task = tasks.get(0);
		List<VariableValue> vars = new ArrayList<VariableValue>();
		vars.add(new VariableValue("approveSteward", "y"));
		workflowService.submitFormTask(task.getAdmItem(), vars);
		
		// the item should still be pending as there is no change
		admItem = adminService.getKeptItem(idItem);
		assertEquals("pending",admItem.getStatus());
		
		// now the approve the registrar
		tasks =  workflowServiceReg.getUserForms().getTaskInstances();
		assertEquals(tasks.size(), 1); // there should only be one pending task for this user
		task = tasks.get(0);
		vars = new ArrayList<VariableValue>();
		vars.add(new VariableValue("approveRegistrar", "y"));
		workflowServiceReg.submitFormTask(task.getAdmItem(), vars);
		
		// the item should still be pending as there is no change
		admItem = adminService.getKeptItem(idItem);
		assertEquals("standard",admItem.getStatus());
		
		
		testRegistrarAcreditation();
	
	}
	
	
	/**
	 * In this test a registrar wants to become a user in the system. The root accreditor authorizes the request.
	 * @throws Exception
	 */
	public void testRegistrarAcreditation() throws Exception{
		WorkflowService rootWebService = WebServiceUtils.getWorkflowService("root", "root");
		WorkflowService guestWorkflowService = WebServiceUtils.getWorkflowService("guest", "guest");

		RegistrationAuthority ra = createRegistrationAuthority();
		
		Registrar r = new Registrar();
		r.setContact_name("Mortimer Squared");
		r.setContact_information(" - ");
		r.setRegistrationAuthority(ra);
		//ra.setOrganization_name("ra2");
		r.setUsername("mortimer");
		r.setEmail("mortimer@squared.com");
			
		String reqTicket = guestWorkflowService.requestAccreditRegistrar(r);
				
		List<String> tickets = rootWebService.getPendingActorRegistrationDecisions();
		assertEquals(tickets.size(), 1);
		String ticket = tickets.get(0);
		assertEquals(reqTicket, ticket);
		
		rootWebService.approveActorRequest(ticket);
		
		tickets = rootWebService.getPendingActorRegistrationDecisions();
		assertTrue(tickets==null);
		
		// the password of mortimer has been sent by email, however we need to change it here for 
		// future tests.
		AccreditorService accServiceSystem = WebServiceUtils.getAccreditorService("system", "system");
		accServiceSystem.updatePasswordForUser("mortimer", "mortimer");
		testAccreditSteward();
		
	}
	
	/**
	 * Tests the accreditation of a steward by request
	 * @throws Exception
	 */
	public void testAccreditSteward() throws Exception {
		
		WorkflowService guestWorkflowService = WebServiceUtils.getWorkflowService("guest", "guest");
		
		Steward s = new Steward();
		s.setContact_name("Steward Micheal");
		s.setContact_information(" - ");
		s.setUsername("michael");
		s.setEmail("michael@stewards.com");
		
		String reqTicket = guestWorkflowService.requestAccreditActor(s, "mortimer");
		
		WorkflowService regWebService = WebServiceUtils.getWorkflowService("mortimer", "mortimer");

		
		List<String> tickets = regWebService.getPendingActorRegistrationDecisions();
		assertEquals(tickets.size(), 1);
		String ticket = tickets.get(0);
		assertEquals(reqTicket, ticket);
		
		regWebService.approveActorRequest(ticket);
		
		tickets = regWebService.getPendingActorRegistrationDecisions();
		assertTrue(tickets==null);
		
		// the password of michael has been sent by email, however we need to change it here for 
		// future tests.
		AccreditorService accServiceSystem = WebServiceUtils.getAccreditorService("system", "system");
		accServiceSystem.updatePasswordForUser("michael", "michael");
		testAccreditSubmitter();
		
	}
	
public void testAccreditSubmitter() throws Exception {
		
		WorkflowService guestWorkflowService = WebServiceUtils.getWorkflowService("guest", "guest");
		
		Submitter s = new Submitter();
		s.setContact_name("Paul Submitter");
		s.setContact_information(" - ");
		s.setUsername("paul");
		s.setEmail("paul@submitters.com");
		
		String reqTicket = guestWorkflowService.requestAccreditActor(s, "michael");
		
		WorkflowService steWebService = WebServiceUtils.getWorkflowService("michael", "michael");

		
		List<String> tickets = steWebService.getPendingActorRegistrationDecisions();
		assertEquals(tickets.size(), 1);
		String ticket = tickets.get(0);
		assertEquals(reqTicket, ticket);
		
		steWebService.approveActorRequest(ticket);
		
		tickets = steWebService.getPendingActorRegistrationDecisions();
		assertTrue(tickets==null);
		
		// the password of michael has been sent by email, however we need to change it here for 
		// future tests.
		AccreditorService accServiceSystem = WebServiceUtils.getAccreditorService("system", "system");
		accServiceSystem.updatePasswordForUser("paul", "paul");
		
		// now lets submit a model using the submitter
		AdministrationService admService = WebServiceUtils.getAdministrationService("paul", "paul");
		byte[] someModel = "somemodel".getBytes();
		// upload any file
		long id = admService.registerAndUpload(createKeptItem(), someModel);
		
		byte[] arr =admService.getModel(id);
		assertEquals(new String(arr), new String(someModel));
		
		//lets test the query item function
		
		List<KeptItem> qItems = admService.queryItems("%", getDate(1,1,2009), getDate(1,1,2011), "%");
		for (KeptItem k:qItems) {
			System.out.println(k.getId()+" *** "+k.getHeader().getDescription()+" status:"+k.getStatus());
			assertEquals("Ket header", k.getHeader().getDescription());
		}
		
	}


	
}
