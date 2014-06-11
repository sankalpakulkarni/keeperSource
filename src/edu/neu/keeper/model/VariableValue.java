package edu.neu.keeper.model;
/**
 * A variable/value tuple.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
public class VariableValue {

	private String variableName;
	private Object value;
	
	public VariableValue() {
		
	}
	
	public VariableValue(String variableName, Object value) {
		super();
		this.variableName = variableName;
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}
