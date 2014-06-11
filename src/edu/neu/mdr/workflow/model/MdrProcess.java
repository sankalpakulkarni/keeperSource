package edu.neu.mdr.workflow.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * This class represents a whole workflow definition. It includes a name (identifier), a start state
 * and a series of other states (including end states). 
 * 
 * The start state should not be part of the general collection of states.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@XmlRootElement(name="process")
@Entity
public class MdrProcess {
	@Id
	private String name;
	//@OneToOne
	private String startState;
	@OneToMany(targetEntity=State.class)
	private List<State> states;
	
	/**
	 * @return the name of this worlflow (this should be unique and have no spaces)
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the start state of this workflow
	 */
	public String getStartState() {
		return startState;
	}
	public void setStartState(String startState) {
		this.startState = startState;
	}
	/**
	 * @return the list of states that make up this workflow (does not contain the start state)
	 */
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
	
	
	
	
}
