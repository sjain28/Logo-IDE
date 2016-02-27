package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends ExpressionNode {

	private List<Command> myChildren;
	private Command myCommand;
	private DoubleProperty myValue;
	
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
	
	public Command getCommand(){
		return myCommand;
	}
	
	public DoubleProperty getValue(){
		return myValue;
	}
	
}
