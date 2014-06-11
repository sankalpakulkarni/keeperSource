package edu.neu.keeper.model;

import javax.persistence.Entity;

/**
 * An Organization shall be identified as the steward responsible for
 * administering each Administered Item, as represented by the relationship
 * Stewardship in Figure 4. This relationship identifies a stewardship contact
 * for the Administered Item.
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 * 
 */
@Entity
public class Steward extends Submitter{
	
	@Override
	public KeeperRole getRole() {
		return KeeperRole.steward;
	}
}
