package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends ExpressionNode {

	private List<Command> myChildren;
	private Command myCommand;
	
	public CommandNode(String name) {
		super(name);
		myChildren = new ArrayList<Command>();
	}
	
	protected void setCommand(Command c){
		myCommand = c;
	}
	
	protected void addChildren(List<Command> children){
		myChildren = children;
	}
	
	public Command getValue(){
		return myCommand;
	}

}
