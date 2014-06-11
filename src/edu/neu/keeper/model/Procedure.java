package edu.neu.keeper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import edu.neu.mdr.workflow.model.MdrProcess;
/**
 * Represents a procedure for registering a given kept item
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class Procedure implements Comparable<Procedure> {
	
	@Id @GeneratedValue
	
	private long id;
	/**
	 * A procedure submitted by a steward needs approval by the registrar.
	 */
	private boolean approved;
	/**
	 * the description of this procedure
	 */
	private String descrpition;
	/**
	 * the mdr process that regulates this procedure
	 */
	@ManyToOne
	private MdrProcess process;
	
	@ManyToOne(optional=false)
	private Steward owner;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getDescrpition() {
		return descrpition;
	}
	public void setDescrpition(String descrpition) {
		this.descrpition = descrpition;
	}
	public MdrProcess getProcess() {
		return process;
	}
	public void setProcess(MdrProcess process) {
		this.process = process;
	}
	@Override
	public int compareTo(Procedure o) {
		return ((Long)this.getId()).compareTo(o.getId());
	}
	public Steward getOwner() {
		return owner;
	}
	public void setOwner(Steward owner) {
		this.owner = owner;
	}
	

}
