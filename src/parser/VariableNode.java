package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import value.Value;
import value.VariableValue;

public class VariableNode extends ExpressionNode{
	
	private VariableValue myValue; //In implementation, this DoubleOptional will be a value in a Map	
	
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
