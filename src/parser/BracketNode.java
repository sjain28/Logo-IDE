package parser;

import java.util.ArrayList;
import java.util.List;

public class BracketNode extends ExpressionNode{

	private List<ExpressionNode> myChildren;
	
	public BracketNode(String val) {
		super(val);
		myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public Object getValue() {
		return myChildren;
	}

}
