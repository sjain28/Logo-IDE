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
	
	@Override
	public void parse(Scope e)throws Exception{
		for(ExpressionNode child: getChildren()){
			if(child instanceof CommandNode){
				addElement(((CommandNode) child).getCommand());
			}else{
				addElement(child.getValue());
			}
			child.parse(p);
		}
	}
}
