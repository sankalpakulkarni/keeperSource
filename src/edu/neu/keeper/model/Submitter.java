package edu.neu.keeper.model;

import javax.persistence.Entity;
/**
 *  * For each Administered Item, an Organization shall be identified as the
 * submitter as represented by the relationship Submission in Figure 4. This
 * relationship identifies a submission contact for the Administered Item
 * 
 * Adapted from ISO/IEC 11179-3 standard by Ken Baclawski (kenb@ccs.neu.edu)
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class Submitter extends User {
	@Override
	public KeeperRole getRole() {
		return KeeperRole.submitter;
	}
}
