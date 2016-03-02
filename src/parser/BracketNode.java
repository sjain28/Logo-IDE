package parser;

import java.util.ArrayList;
import java.util.List;

public class BracketNode extends ExpressionNode{

	private List<Object> bracketContents;
	
	public BracketNode(String val) {
		super(val);
		bracketContents = new ArrayList<Object>();
	}

	@Override
	public Object getValue() {
		return (Iterable<Object>) bracketContents;
	}
	
	public void addElement(Object element){
		bracketContents.add(element);
	}
}
