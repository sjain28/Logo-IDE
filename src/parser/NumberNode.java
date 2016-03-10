package parser;

import value.NumericalValue;
import value.Value;

public class NumberNode extends ExpressionNode{

	private Value myValue;
	
	/**
	 * Precondition: Input name must be a number
	 */
	public NumberNode(String name) {
		super(name);
		myValue = new NumericalValue(Double.parseDouble(name));
	}

	@Override
	public Value getValue() {
		return myValue;
	}
	
	public void parse(Scope e){
		return;
	}
}
