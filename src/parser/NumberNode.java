package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import value.NumericalValue;
import value.DoubleOptional;

public class NumberNode extends ExpressionNode{

	private NumericalValue myValue;
	
	/**
	 * Precondition: Input name must be a number
	 */
	public NumberNode(String name) {
		super(name);
		myValue = new NumericalValue(Double.parseDouble(name));
	}

	@Override
	public DoubleOptional getValue() {
		return myValue;
	}
	
	public List<Command> parse(){
		return new ArrayList<Command>();
	}
}
