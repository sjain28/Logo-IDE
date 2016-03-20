package value;

import parser.Environment;

public class VariableValue extends Value {
	private String varName;
	private Environment myEnvironment;

	public VariableValue(String varName) {
		super();
		this.varName = varName;
	}
	
	@Override
	public Double getValue() { return myEnvironment.getVariable(varName); }
	
	@Override
	public void setValue(double d) {
		super.setValue(d);
		myEnvironment.setVariable(varName, d);
	}
	
	public String getName() { return varName; }

	public void setEnvironment(Environment env) {
		myEnvironment = env;
	}
	


}
