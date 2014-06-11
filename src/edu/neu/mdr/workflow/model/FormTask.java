package edu.neu.mdr.workflow.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 * A form task is a state in which a particular user needs 
 * to feed the system with information so that the workflow can continue.
 * Future decision states may based the decision depending on the values 
 * filled by the user. 
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class FormTask extends State {
	
	private String asignee;
	private String instructions;
	@OneToMany
	private List<Field> fields;
	
	/**
	 * @return the fields that need to be completed in this form state
	 */
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * @return the instructions regarding the completion of this form
	 */
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the person to which this task is assigned to
	 */
	public String getAsignee() {
		return asignee;
	}

	public void setAsignee(String asignee) {
		this.asignee = asignee;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		FormTask fm = (FormTask) obj;
		return this.getId()==fm.getId();
	}
	
	/**
	 * @return a set with all the field names contained in this form task
	 */
	@Transient
	@XmlTransient
	public Set<String> getFieldNames() {
		HashSet<String> ret = new HashSet<String>();
		for (Field f:fields) {
			ret.add(f.getName());
		}
		return ret;
	}
	
}
