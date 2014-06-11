package edu.neu.mdr.workflow.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlSeeAlso;
/**
 * A generic state in the workflow. Consists of a name and a list of transitions.
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 *
 *
 */
@XmlSeeAlso({
    DecisionState.class,
    EndState.class,
    FormTask.class,
    ChangeVisibilityState.class,
    ModifyStatusState.class,
    ScriptTask.class
})
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class State {
	@Id @GeneratedValue 
	private long id;
	private String name;
	@OneToMany
	private List<Transition> transitions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
