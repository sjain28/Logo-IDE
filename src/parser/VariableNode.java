package parser;

import value.Value;
import value.VariableValue;

public class VariableNode extends ExpressionNode{
	
	private Value myValue; //In implementation, this DoubleOptional will be a value in a Map	
	
	public VariableNode(String name) {
		super(name);
		myValue = new VariableValue(name);
	}

	@Override
	public Value getValue() {
		return myValue;
	}
	
	@Override
	public void parse(Scope e){
		return;
	}
}
