package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import value.Value;
import value.VariableValue;

// This code is part of my code masterpiece

public class VariableNode extends ExpressionNode{
	
	private VariableValue myValue;	
	
	public VariableNode(String name) {
		super(name);
		myValue = new VariableValue(name);
	}

	@Override
	public void setEnvironment(Environment env) { 
		super.setEnvironment(env);
		myValue.setEnvironment(env);
	}
	
	@Override
	public Value getValue() {
		return myValue;
	}
	
	@Override
	public List<Command> parse(){
		return new ArrayList<Command>();
	}
}
