package parser;

public class ValueNode extends ExpressionNode{

	private double myValue;
	
	/*
	 * Precondition: Input name must be a number
	 */
	public ValueNode(String name) {
		super(name);
		myValue = Double.parseDouble(name);
	}

	@Override
	public Object getValue() {
		return myValue;
	}

}
