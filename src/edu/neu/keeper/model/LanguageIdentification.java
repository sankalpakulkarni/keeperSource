package edu.neu.keeper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The composite datatype Language Identification serves as an identifier for a
 * language. It is used in: (1) the Registration Authority class to identify the
 * default language(s) of the registration authority: (2) the Reference Document
 * class to identify the language(s) used within the document: (3) the Language
 * Section class of the Naming and Definition region to identify the language
 * used for names and definitions within that section. The identifier comprises
 * a mandatory language identifier and an optional country identifier, the
 * latter being used to distinguish variations in language use in different
 * countries.
 * 
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */

@Entity
public class LanguageIdentification {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false)
	private String language_identifier; // Should not be null

	private String country_identifier;

	public String getLanguage_identifier() {
		return language_identifier;
	}

	public void setLanguage_identifier(String languageIdentifier) {
		language_identifier = languageIdentifier;
	}

	public String getCountry_identifier() {
		return country_identifier;
	}

	public void setCountry_identifier(String countryIdentifier) {
		country_identifier = countryIdentifier;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
