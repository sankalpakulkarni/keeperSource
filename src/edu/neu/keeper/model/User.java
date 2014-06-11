package edu.neu.keeper.model;

import javax.persistence.Entity;
/**
 * Any user in the Keeper System.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class User extends Contact {

	@Override
	public KeeperRole getRole() {
		return KeeperRole.readOnlyUser;
	}
	
}
