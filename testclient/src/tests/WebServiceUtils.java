package tests;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.mdr.wsdl.AccreditorService;
import org.mdr.wsdl.AdministrationService;
import org.mdr.wsdl.WorkflowService;


/**
 * This is a util class that returns a proxy that implements one of the interfaces:
 * Workflow, Administration and Accreditor
 * 
 * It also automatically injects credentials to the proxy by using handlers.
 *  
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class WebServiceUtils {
	/**
	 * @return Returns a proxy for the accreditor service
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static AccreditorService getAccreditorService(String username,String password) throws Exception {
		Service accreditorService = Service.create(new URL(
				"http://127.0.0.1:4204/Accreditor?wsdl"), new QName(
				"http://mdr.org/wsdl", "AccreditorWebService"));

		AccreditorService acrWs = accreditorService
				.getPort(AccreditorService.class);
		
		attachCredentials(username, password, acrWs);
		
		return acrWs;
	}
	
	/**
	 * @return Returns a proxy for the workflow service
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static WorkflowService getWorkflowService(String username,String password) throws Exception {
		Service wkfService = Service.create(new URL(
				"http://127.0.0.1:4204/Workflow?wsdl"), new QName(
				"http://mdr.org/wsdl", "WorkflowWebService"));

		WorkflowService acrWs = wkfService
				.getPort(WorkflowService.class);
		
		attachCredentials(username, password, acrWs);
		
		return acrWs;
	}
	
	/**
	 * @return returns a  proxy for the administration service
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static AdministrationService getAdministrationService(String username,String password) throws Exception {
		Service admService = Service.create(new URL(
				"http://127.0.0.1:4204/Administration?wsdl"), new QName(
				"http://mdr.org/wsdl", "AdministrationWebService"));

		AdministrationService acrWs = admService
				.getPort(AdministrationService.class);
		
		attachCredentials(username, password, acrWs);

		return acrWs;
	}
	

	private static void attachCredentials(final String username,
			final String password, Object wsBean) {

		Client client = ClientProxy.getClient(wsBean);
		Endpoint endpoint = client.getEndpoint();
		endpoint.getOutInterceptors().add(new SAAJOutInterceptor());

		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, username);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {

			public void handle(Callback[] callbacks) throws IOException,
					UnsupportedCallbackException {
				WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
				pc.setPassword(password);
			}
		});

		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wssOut);
	}
}
