package edu.neu.mdr.workflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;
/**
 * A transition from one state into another.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
@Table(name="STATE_TRANSITION")
@Inheritance(strategy=InheritanceType.JOINED)
@XmlSeeAlso({
    ConditionTransition.class
})
public class Transition {
	@Id @GeneratedValue
	private long id;
	
	private String transitionName;
	
	private String toState;

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
}
