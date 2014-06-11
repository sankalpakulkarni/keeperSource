package edu.neu.mdr.workflow.model;

import javax.persistence.Entity;
/**
 * A general state that executes a script.
 * TODO: think about this. it might be dangerous in terms of
 * security as any registrar is able to submit workflow definitions.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class ScriptTask extends State {
	
	private String script;

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
	
	
}
