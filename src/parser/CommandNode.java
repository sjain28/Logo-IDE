package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends ExpressionNode {

	private Command myCommand;
	
	public CommandNode(String name, Command c) {
		super(name);
		myCommand = c;
	}

	public Command getCommand(){
		return myCommand;
	}
	
	public DoubleOptional getValue(){
		return myCommand.getValue();
	}
	
}
