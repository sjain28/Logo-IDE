package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.ControlCommand;
import commands.TurtleCommand;

public class CommandNode extends ExpressionNode {

	private Command myCommand;
	private DoubleOptional myValue;
	
	public CommandNode(String name, Command c) {
		super(name);
		myCommand = c;
		myValue = myCommand.getValue();
	}

	public Command getCommand(){
		return myCommand;
	}
	
	public DoubleOptional getValue(){
		return myValue;
	}
	
	public void parse(Parser p) throws Exception{
		getCommand().setParser(p); // Get "environment" for the ControlCommand to affect

		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: getChildren()){
			params.add(child.getValue());
			child.parse(p);
		}
		
		getCommand().setParams(params);
		

		
		if(hasParent() && (getParent() instanceof BracketNode)){
			return;
		}
		p.addCommand(getCommand());
	}
}
