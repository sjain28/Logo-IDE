package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends ExpressionNode {

	private List<Command> myChildren;
	private Command myCommand;
	private DoubleOptional myValue;
	
	public CommandNode(String name, Command c) {
		super(name);
		myCommand = c;
		myChildren = new ArrayList<Command>();
		myValue = new DoubleOptional();
	}
	
	protected void addChildren(List<Command> children){
		myChildren = children;
	}
	
	public Command getCommand(){
		return myCommand;
	}
	
	public DoubleOptional getValue(){
		return myValue;
	}
	
}
