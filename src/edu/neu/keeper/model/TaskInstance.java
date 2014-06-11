package edu.neu.keeper.model;

import edu.neu.mdr.workflow.model.FormTask;
/**
 * Encapuslates the kept item and and a FormTask in a single object.  
 *  
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class TaskInstance {
	private Long admItem;
	private FormTask task;
	
	public TaskInstance() {
	}

	public TaskInstance(Long processId, FormTask task) {
		super();
		this.admItem = processId;
		this.task = task;
	}
	
	public Long getAdmItem() {
		return admItem;
	}

	public void setAdmItem(Long admItem) {
		this.admItem = admItem;
	}

	public FormTask getTask() {
		return task;
	}
	public void setTask(FormTask task) {
		this.task = task;
	}
	
	
	
	
}
