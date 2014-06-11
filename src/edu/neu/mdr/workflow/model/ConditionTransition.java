package edu.neu.mdr.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * A condition transition has an expression that evaluates to true or false. 
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class ConditionTransition extends Transition {
	@Column(name="cond")
	private String condition;

	/**
	 * Sets the condition for this transition
	 * @param condition the expression (see JBMP for allowed expressions)
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}
	
}
