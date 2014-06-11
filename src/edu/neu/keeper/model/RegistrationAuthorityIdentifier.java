package edu.neu.keeper.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite datatype Registration Authority Identifier is used to uniquely
 * identify a Registration Authority. The sources of values for each part of the
 * identifier are specified in ISO/IEC 11179-6.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Embeddable
public class RegistrationAuthorityIdentifier {

	private String OPI_source;
	private String organization_part_identifier;
	@Column(nullable=false)
	private String organization_identifier; // Should not be null
	@Column(nullable=false)
	private String international_code_designator; // Should not be null

	public String getOPI_source() {
		return OPI_source;
	}

	public void setOPI_source(String oPISource) {
		OPI_source = oPISource;
	}

	public String getOrganization_part_identifier() {
		return organization_part_identifier;
	}

	public void setOrganization_part_identifier(
			String organizationPartIdentifier) {
		organization_part_identifier = organizationPartIdentifier;
	}

	public String getOrganization_identifier() {
		return organization_identifier;
	}

	public void setOrganization_identifier(String organizationIdentifier) {
		organization_identifier = organizationIdentifier;
	}

	public String getInternational_code_designator() {
		return international_code_designator;
	}

	public void setInternational_code_designator(
			String internationalCodeDesignator) {
		international_code_designator = internationalCodeDesignator;
	}

}
