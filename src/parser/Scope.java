package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import commands.UserDefinedFunction;
import turtle.Agent;
import commands.Command;

public class Scope implements Environment {
	private Map<String, Double> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
//	private List<Command> myCommands;
	private Environment myParent;
	private int myDepth;
	private List<Agent> myTurtles;
	private List<Agent> myActiveTurtles;
	
	public Scope() {
		myVariables = new HashMap<String, Double>();
		myFunctions = new HashMap<String, UserDefinedFunction>();
		myTurtles = new ArrayList<Agent>();
		myActiveTurtles = new ArrayList<Agent>();
	}
	public Scope(int depth) {
		this();
		myDepth = depth;
	}
	
	public void setParent(Scope parent) {
		myParent = parent;
		myDepth = parent.getDepth() + 1;
	}
	
	@Override
	public Double getVariable(String varName) {
		return (myVariables.containsKey(varName) ? myVariables.get(varName) : (myParent != null? myParent.getVariable(varName) : null));
	}
	
	@Override
	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
	
	@Override
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }
		
	@Override
	public List<Agent> getTurtles() {
		return (!myTurtles.isEmpty()? new ArrayList<Agent>(myTurtles) : myParent.getTurtles());
	}
	@Override
	public void setTurtles(List<Agent> newTurtles) {
		
	}
	@Override
	public List<Agent> getActiveTurtles() {
		return (!myActiveTurtles.isEmpty()? new ArrayList<Agent>(myActiveTurtles) : myParent.getActiveTurtles());
	}
	@Override
	public void setActiveTurtles(List<Agent> newActiveTurtles) {
		myActiveTurtles = newActiveTurtles;
	}
	
	public int getDepth() { return myDepth; }
	
}
