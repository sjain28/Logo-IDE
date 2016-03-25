package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class BracketNode extends ExpressionNode{

	private List<Object> bracketContents;
	
	public BracketNode(String val) {
		super(val);
		bracketContents = new ArrayList<Object>();
	}

	@Override
	public Object getValue() {
		return (List<Object>) bracketContents;
	}
	
	public int getNumChildren() {
		return getChildren().size();
	}
	
	public void addElement(Object element){
		bracketContents.add(element);
	}
	
	@Override
	public List<Command> parse() throws Exception{
		for(ExpressionNode child: getChildren()){
			if(child instanceof CommandNode){
				List<Command> commands = child.parse();
				for(Command c : commands) {
					addElement(c);
				}
			}else{
				addElement(child.getValue());
				child.parse();
			}
		}
		return new ArrayList<Command>();
	}
}
