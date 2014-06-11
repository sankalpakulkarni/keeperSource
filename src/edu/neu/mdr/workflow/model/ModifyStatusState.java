package edu.neu.mdr.workflow.model;

import javax.persistence.Entity;
/**
 * A state that modifies that status of the kept item 
 * being subject to the process. 
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class ModifyStatusState extends State {
	private String newStatus;

	/**
	 * @return the new status
	 */
	public String getNewStatus() {
		return newStatus;
	}
	/**
	 * sets the new status for a kept item reaching this state
	 * @param newStatus
	 */
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	
	
}
