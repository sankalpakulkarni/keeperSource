package edu.neu.keeper.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * A Registration Authority is any Organization authorized to register metadata.
 * A Registration Authority is a subtype of Organization and inherits all of its
 * attributes and relationships. An Administered Item has a Registration
 * Authority that is its owner, shown by the relationship registration in Figure
 * 4. A Registration Authority may register many Administered Items.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Entity
public class RegistrationAuthority extends Organization {
	private RegistrationAuthorityIdentifier registration_authority_identifier; // Should not be null
	@ManyToMany // Should not be empty
	private List<LanguageIdentification> documentation_language_identifier = new ArrayList<LanguageIdentification>(); 

	public RegistrationAuthorityIdentifier getRegistration_authority_identifier() {
		return registration_authority_identifier;
	}

	public void setRegistration_authority_identifier(
			RegistrationAuthorityIdentifier registrationAuthorityIdentifier) {
		registration_authority_identifier = registrationAuthorityIdentifier;
	}

	public List<LanguageIdentification> getDocumentation_language_identifier() {
		return documentation_language_identifier;
	}

	public void setDocumentation_language_identifier(
			List<LanguageIdentification> documentationLanguageIdentifier) {
		documentation_language_identifier = documentationLanguageIdentifier;
	}

}
