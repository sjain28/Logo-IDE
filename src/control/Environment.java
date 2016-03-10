package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import commands.UserDefinedFunction;
import parser.DoubleOptional;
import turtle.Agent;
import commands.Command;

public class Environment {
	private Map<String, DoubleOptional> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
	private List<Command> myCommands;
	private Environment myParent;
	private List<Environment> myChildren;
	private List<Agent> myTurtles;
	private List<Agent> activeTurtles;
	private int mydepth;
	
	public Environment() {
		myVariables = new HashMap<String, DoubleOptional>();
		myFunctions = new HashMap<String, UserDefinedFunction>();
	}
	
	public Map<String, DoubleOptional> getVariables() { 
		return myVariables; 
	}
	
	public List<Agent> getTurtles(){
		if(myTurtles != null)
			return myTurtles;
		return myParent.getTurtles();
	}
	
	public List<Agent> getActiveTurtles(){
		if(activeTurtles != null)
			return activeTurtles;
		return myParent.getActiveTurtles(); 
	}
	
	public void setActiveTurtles(List<Agent> actives){
		activeTurtles = actives;
	}
	
	public double getVariable(String var){
		return -1;
	}
	
	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }

}
