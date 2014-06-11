package edu.neu.keeper.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * A Registration Authority is represented by one or more Registrars as shown by
 * the relationship reference authority registrar in Figure 4. Registrars are
 * the persons who perform the administrative steps to register Administered
 * Items in a Metadata Registry.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Entity
public class Registrar extends Steward { 
	@ManyToOne(optional=false)
	private RegistrationAuthority registrationAuthority;

	public RegistrationAuthority getRegistrationAuthority() {
		return registrationAuthority;
	}

	public void setRegistrationAuthority(RegistrationAuthority registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}
	
	@Override
	public KeeperRole getRole() {
		return KeeperRole.registrar;
	}
	
}
