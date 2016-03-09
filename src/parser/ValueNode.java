package parser;

public class ValueNode extends ExpressionNode{

	private DoubleOptional myValue;
	
	/**
	 * Precondition: Input name must be a number
	 */
	public ValueNode(String name) {
		super(name);
		myValue = new DoubleOptional(Double.parseDouble(name));
	}

	@Override
	public DoubleOptional getValue() {
		return myValue;
	}
	
	public void parse(Parser p){
		return;
	}
}
