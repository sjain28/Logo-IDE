package commands;

import java.util.List;

import value.Value;

public final class UserDefinedFunction extends ControlCommand {
	private String functionName;
	private List<Value> myParams;
	private List<Value> myParamValues;
	private int numParams;
	private List<Command> commandList;
	
	public UserDefinedFunction() {
		
	}
	
	public void defineFunction(String functionName, List<Value> params, List<Command> commandList) {
		this.functionName = functionName;
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
		myParamValues = (List<Value>) params.get(0);
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
