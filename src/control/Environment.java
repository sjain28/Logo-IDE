package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import commands.UserDefinedFunction;
import parser.DoubleOptional;
import commands.Command;

public class Environment {
	private Map<String, DoubleOptional> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
	private List<Command> myCommands;
	private Environment myParent;
	private List<Environment> myChildren;
	private int mydepth;
	
	public Environment() {
		myVariables = new HashMap<String, DoubleOptional>();
		myFunctions = new HashMap<String, UserDefinedFunction>();
	}
	
	public Map<String, DoubleOptional> getVariables() { 
		return myVariables; 
	}
	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }

}
