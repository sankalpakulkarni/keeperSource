package edu.neu.keeper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 
 * An Organization can play one or more roles with respect to a Metadata
 * Registry. The roles currently recognized in this part of ISO/IEC 11179 are:
 * Registration Authority, reference organization, steward (of an Administered
 * Item) - represented by the relationship stewardship - and submitter (of an
 * Administered Item) - represented by the relationship submission.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Organization {
	private String organization_mail_address;

	@Id
	// TODO: name is ID?
	@Column(nullable=false)
	private String organization_name; // Should not be null

	public String getOrganization_mail_address() {
		return organization_mail_address;
	}

	public void setOrganization_mail_address(String organizationMailAddress) {
		organization_mail_address = organizationMailAddress;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organizationName) {
		organization_name = organizationName;
	}

}
