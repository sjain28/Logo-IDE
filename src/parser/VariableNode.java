package parser;

public class VariableNode extends ExpressionNode {
	
	public VariableNode(String name, int value) {
		super(name);
	}
	
	public String getValue(){
		return super.getName(); //This is ugly. 
	}

}
