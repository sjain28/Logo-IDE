package commands;

import java.util.List;

import value.Value;
import value.VariableValue;

public final class UserDefinedFunction extends BlockCommand {
	private String functionName;
	private List<VariableValue> myVariables;
	private List<Command> myCommands;
	
	public UserDefinedFunction(String functionName) {
		this.functionName = functionName;
	}
	
	public void defineFunction(List<VariableValue> variableList, List<Command> commandList) {
		this.myVariables = variableList;
		this.myCommands = commandList;
	}

	public String getName() {
		return functionName;
	}
	
	@Override
	public void setNumParams(int n) {
		super.setNumParams(n);
	}
	
	@Override
	public double evaluate() {
		try {
			List<Value> myParamValues = getParams();
			for(int i = 0; i < myVariables.size(); i++) {
				myVariables.get(i).setValue(myParamValues.get(i).getValue());
			}
		}
		catch (Exception e) {
			return 0;
		}
		double value = 0;
		for (Command c : myCommands) {
			value = c.evaluate();
		}
		return value;
	}

}
