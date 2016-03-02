package commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import parser.DoubleOptional;
import parser.Parser;

public class MakeUserInstruction extends ControlCommand {
	String functionName;
	List<DoubleOptional> myVariables;
	List<Command> myCommands;
	
	public MakeUserInstruction() {
	}

	@Override
	public int getNumParams() {
		return 3;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try {
			DoubleOptional commandVariable = (DoubleOptional) params.get(0);
			Map<String, DoubleOptional> variablesMap = getParser().getVariables();
			functionName = variablesMap.keySet().stream().filter(key -> variablesMap.get(key) == commandVariable).collect(Collectors.toList()).get(0);
			variablesMap.remove(functionName);
			getParser().addFunction(functionName, null);
			
			myVariables = (List<DoubleOptional>) params.get(1);
			myCommands = (List<Command>) params.get(2);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		if(functionName == null) {
			return 0;
		}
		UserDefinedFunction newFunction = new UserDefinedFunction();
		newFunction.defineFunction(functionName, myVariables, myCommands);
		getParser().addFunction(functionName, newFunction);
		return 1;
	}

}
