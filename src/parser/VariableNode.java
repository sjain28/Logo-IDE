package parser;

public class VariableNode extends ExpressionNode{
	
	private DoubleProperty myValue; //In implementation, this doubleproperty will be a value in a Map
	
	
	public VariableNode(String name, DoubleProperty val) {
		super(name);
		myValue = val;
	}


	@Override
	public DoubleProperty getValue() {
		return myValue;
	}
}
