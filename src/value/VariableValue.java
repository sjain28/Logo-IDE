package value;

import parser.Environment;

public class VariableValue extends Value {
	private String varName;
	private Environment myEnvironment;

	public VariableValue(String varName) {
		super();
		this.varName = varName;
	}
	
	public VariableValue(String varName, Environment e) {
		this(varName);
		setEnvironment(e);
	}	
	
	
	@Override
	public Double getValue() { return myEnvironment.getVariable(varName); }
	
	public String getName() { return varName; }

	public void setEnvironment(Environment e) {
		myEnvironment = e;
	}
	


}
