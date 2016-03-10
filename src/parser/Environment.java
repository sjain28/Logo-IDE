package parser;

import java.util.List;
import java.util.Map;

import commands.UserDefinedFunction;
import turtle.Agent;
import value.Value;

public interface Environment {
	
	public Double getVariable(String varName);

	public Map<String, UserDefinedFunction> getFunctions();

	public void addFunction(String functionName, UserDefinedFunction function);

	public List<Agent> getTurtles();
	public void setTurtles(List<Agent> newTurtles);
	public List<Agent> getActiveTurtles();
	public void setActiveTurtles(List<Agent> newActiveTurtles);
	
	
}