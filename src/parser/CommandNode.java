package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends ExpressionNode {

	private Command myCommand;
	private DoubleOptional myValue;
	
	public CommandNode(String name, Command c) {
		super(name);
		myCommand = c;
		myValue = myCommand.myValue; //CHANGE THIS 
	}

	public Command getCommand(){
		return myCommand;
	}
	
	public DoubleOptional getValue(){
		return myValue;
	}
	
}
