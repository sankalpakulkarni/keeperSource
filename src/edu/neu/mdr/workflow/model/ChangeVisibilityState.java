package edu.neu.mdr.workflow.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * A state that modifies the visibility of the kept item 
 * being subject to the process. 
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
@XmlRootElement(name="changeVisibility")
public class ChangeVisibilityState extends State {
	private String newVisibility;

	/**
	 * @return the new status
	 */
	public String getNewVisibility() {
		return newVisibility;
	}
	/**
	 * sets the new status for a kept item reaching this state
	 * @param newStatus
	 */
	public void setNewVisibility(String newVis) {
		this.newVisibility = newVis;
	}
	
	
}
