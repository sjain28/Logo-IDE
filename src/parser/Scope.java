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
	public Double getVariable(String varName) {
		return (myVariables.containsKey(varName) ? myVariables.get(varName) : (myParent != null? myParent.getVariable(varName) : null));
	}
	
	@Override
	public Map<String, Double> getVariables() {
		Map<String, Double> result = new HashMap<String, Double>(myVariables);
		if (myParent != null) {
			result.entrySet().addAll(myParent.getVariables().entrySet());
		}
		return result;
	}
	@Override
	public Map<String, UserDefinedFunction> getUserDefinedFunctions() { 
		Map<String, UserDefinedFunction> result = new HashMap<String, UserDefinedFunction>(myFunctions);
		if (myParent != null) {
			result.entrySet().addAll(myParent.getUserDefinedFunctions().entrySet());
		}
		return result;
	}
	
	@Override
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }
		
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
		return (!myTurtles.isEmpty()? new ArrayList<Agent>(myTurtles) : myParent.getTurtles());
	}
	@Override
	public void setTurtles(List<Agent> newTurtles) {
		myTurtles = newTurtles;
	}
	@Override
	public void addTurtle(Agent additionalTurtle) {
		myTurtles.add(additionalTurtle);
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
		myActiveTurtles.add(additionalActiveTurtle);
	}
	public int getDepth() { return myDepth; }
	
}
