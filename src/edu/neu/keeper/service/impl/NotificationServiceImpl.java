package edu.neu.keeper.service.impl;

import java.io.StringWriter;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXB;

import edu.neu.keeper.model.Contact;
import edu.neu.keeper.service.NotificationService;
import edu.neu.keeper.service.RepositoryException;

@Stateless(name="Notification")
public class NotificationServiceImpl implements NotificationService {

	@PersistenceContext(unitName="mdr")
	EntityManager entityManager;
	
	@Override
	public void notifyMessage(String username, String text)
			throws RepositoryException {
		Contact c = entityManager.find(Contact.class, username);
		// TODO Use a email api to send email
		System.out.println("User:"+username);
		System.out.println("Email:"+c.getEmail());
		System.out.println("Message:"+text);
	}
	
	@Override
	public void notifyByEmail(String user,String email, String text)
			throws RepositoryException {
		// TODO Use a email api to send email
		System.out.println("User:"+user);
		System.out.println("Email:"+email);
		System.out.println("Message:"+text);
	}

	@Override
	public void notifyMessage(String username, String text, List<Object> objects)
			throws RepositoryException {
		Contact c = entityManager.find(Contact.class, username);
		// TODO Use a email api to send email
		System.out.println("User:"+username);
		System.out.println("Email:"+c.getEmail());
		System.out.println("Message:"+text);
		for (Object obj:objects) {
			StringWriter sw = new StringWriter();
			System.out.println("Object:\nBEGIN\n");
			JAXB.marshal(obj, sw);
			System.out.println(sw);
			System.out.println("\nEND");
		}
	}

}
