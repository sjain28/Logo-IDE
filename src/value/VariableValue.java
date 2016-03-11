package value;

import parser.Environment;

public class VariableValue extends DoubleOptional {
	private String varName;
	private Environment myEnvironment;

	public VariableValue(String varName) {
		super();
		this.varName = varName;
	}
	
	@Override
	public Double getValue() { return myEnvironment.getVariable(varName); }
	
	public String getName() { return varName; }

	public void setEnvironment(Environment env) {
		myEnvironment = env;
	}
	


}
