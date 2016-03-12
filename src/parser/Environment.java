package parser;

import java.util.List;
import java.util.Map;

import commands.UserDefinedFunction;
import turtle.Agent;
import value.Value;

public interface Environment {
	
	public void setParent(Environment parent);
	public Environment getParent();
	public Environment makeChild();
	
	public void setVariable(String varName, double value);
	public Double getVariable(String varName);
	public Map<String, Double> getVariables();
	public Map<String, UserDefinedFunction> getUserDefinedFunctions();
	public void addFunction(String functionName, UserDefinedFunction function);
	
	public void makeNewTurtle(Agent newTurtle);
	public List<Agent> getTurtles();
	public void addTurtle(Agent additionalTurtle);
	public List<Agent> getActiveTurtles();
	public void setActiveTurtles(List<Agent> newActiveTurtles);
	public void addActiveTurtle(Agent additionalActiveTurtle);

	
}