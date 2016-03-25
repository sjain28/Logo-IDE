package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import commands.UserDefinedFunction;
import turtle.Agent;

public class Scope implements Environment {
	private Map<String, Double> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
	private Environment myParent;
	private int myDepth;
	private List<Agent> myTurtles;
	private List<Agent> myActiveTurtles;
	
	public Scope(int depth) {
		myDepth = depth;
		myVariables = new HashMap<String, Double>();
		myFunctions = new HashMap<String, UserDefinedFunction>();
		myTurtles = new ArrayList<Agent>();
		myActiveTurtles = new ArrayList<Agent>();
	}
	
	@Override
	public Environment getParent() {
		return myParent;
	}
	@Override
	public void setParent(Environment parent) {
		myParent = parent;
	}
	
	@Override
	public Scope makeChild() {
		Scope child = new Scope(myDepth + 1);
		child.setParent(this);
		return child;
	}
	
	@Override
	public void setVariable(String varName, double value) {
		myVariables.put(varName, value);
	}
	
	@Override
	public Double getVariable(String varName) {
		return (myVariables.containsKey(varName) ? myVariables.get(varName) : (myParent != null? myParent.getVariable(varName) : null));
	}
	
	@Override
	public Map<String, Double> getVariables() {
		if (myParent == null) {
			return new HashMap<String, Double>(myVariables);
		}
		Map<String, Double> result = myParent.getVariables();
		result.putAll(myVariables);
		return result;
	}
	
	@Override
	public UserDefinedFunction getFunction(String functionName) {
		return (myFunctions.containsKey(functionName) ? myFunctions.get(functionName) : (myParent != null? myParent.getFunction(functionName) : null));
	}
	
	@Override
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }
	
	@Override
	public Map<String, UserDefinedFunction> getUserDefinedFunctions() { 
		if (myParent == null) {
			return new HashMap<String, UserDefinedFunction>(myFunctions);
		}
		Map<String, UserDefinedFunction> result = myParent.getUserDefinedFunctions();
		result.putAll(myFunctions);
		return result;
	}
		
	@Override
	public void makeNewTurtle(Agent newTurtle) {
		if (!myTurtles.isEmpty()) {
			myTurtles.add(newTurtle);
		}
		if (myParent != null) {
			myParent.makeNewTurtle(newTurtle);
		}
	}
	@Override
	public List<Agent> getTurtles() {
		return ((myParent == null)? myTurtles : myParent.getTurtles());
	}
	@Override
	public void addTurtle(Agent additionalTurtle) {
		if (myParent == null) {
			myTurtles.add(additionalTurtle);
		}
		else {
			myParent.addTurtle(additionalTurtle);
		}
	}


	@Override
	public List<Agent> getActiveTurtles() {
		return ((myParent == null)? myActiveTurtles : myParent.getActiveTurtles());
	}
	@Override
	public void setActiveTurtles(List<Agent> newActiveTurtles) {
		if (myParent == null) {
			myActiveTurtles = newActiveTurtles;
		}
		else {
			myParent.setActiveTurtles(newActiveTurtles);
		}
	}
	@Override
	public void addActiveTurtle(Agent additionalActiveTurtle) {
		if (myParent == null) {
			myActiveTurtles.add(additionalActiveTurtle);
		}
		else {
			myParent.addActiveTurtle(additionalActiveTurtle);
		}
	}
	
	public int getDepth() { return myDepth; }

	@Override
	public void setAllVariables(Map<String, Double> newVariables) {
		myVariables = newVariables;
	}

	@Override
	public void setAllTurtles(List<Agent> newTurtles) {
		myTurtles = newTurtles;
	}
}
