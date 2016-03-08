package parser;

public class VariableNode extends ExpressionNode{
	
	private DoubleOptional myValue; //In implementation, this DoubleOptional will be a value in a Map	
	
	public VariableNode(String name, DoubleOptional val) {
		super(name);
		//myValue = new DoubleOptional();
		myValue = val;
	}

	@Override
	public DoubleOptional getValue() {
		return myValue;
	}
}
