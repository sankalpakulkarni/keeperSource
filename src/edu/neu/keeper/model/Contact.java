package edu.neu.keeper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The composite datatype Contact is used to specify the contact information for
 * registrar contact, stewardship contact and submission contact.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@XmlSeeAlso({
    Registrar.class,
    Steward.class,
    User.class,
    Submitter.class
})
public class Contact {
	@Column(nullable=false)
	private String contact_information; // Should not be null
	@Column(nullable=false)
	private String contact_name; // Should not be null
	private String contact_title;
	@Id
	private String username;
	@Column(nullable=false)
	private String password;
	@ManyToOne
	private Contact creator;
	@Enumerated(EnumType.STRING)
	private KeeperRole role;
	private String email;


	public String getContact_information() {
		return contact_information;
	}

	public void setContact_information(String contactInformation) {
		contact_information = contactInformation;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contactName) {
		contact_name = contactName;
	}

	public String getContact_title() {
		return contact_title;
	}

	public void setContact_title(String contactTitle) {
		contact_title = contactTitle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlTransient
	public Contact getCreator() {
		return creator;
	}

	public void setCreator(Contact creator) {
		this.creator = creator;
	}
	
	@XmlTransient
	public KeeperRole getRole() {
		return role;
	}

	public void setRole(KeeperRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
