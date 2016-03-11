package commands;

import java.util.List;

import value.DoubleOptional;

public final class UserDefinedFunction extends BlockCommand {
	private String functionName;
	private List<DoubleOptional> myParams;
	private List<DoubleOptional> myParamValues;
	private int numParams;
	private List<Command> commandList;
	
	public UserDefinedFunction(String functionName) {
		this.functionName = functionName;

	}
	
	public void defineFunction(List<DoubleOptional> params, List<Command> commandList) {
		this.myParams = params;
		this.numParams = params.size();
		this.commandList = commandList;
	}

	public String getName() {
		return functionName;
	}
	
	@Override
	public int getNumParams() {
		return numParams;
	}

	@Override
	protected void initParams(List<Object> params){
		myParamValues = (List<DoubleOptional>) params.get(0);
	}
	
	@Override
	public double evaluate() {
		try {
			for(int i = 0; i < numParams; i++) {
				myParams.get(0).setValue(myParamValues.get(0).getValue());
			}
		}
		catch (Exception e) {
			return 0;
		}
		double value = 0;
		for (Command c : commandList) {
			value = c.evaluate();
		}
		return value;
	}

}
