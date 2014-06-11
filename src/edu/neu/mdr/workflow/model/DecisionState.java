package edu.neu.mdr.workflow.model;

import javax.persistence.Entity;
/**
 * A state that includes a decision. 
 * All transitions in a decision state must be of ConditionTransition type.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
//@XmlRootElement(name="decisionState")
public class DecisionState extends State {
	
}
