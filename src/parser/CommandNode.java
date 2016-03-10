package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.ControlCommand;
import commands.TurtleCommand;
import value.Value;

public class CommandNode extends ExpressionNode {

	private Command myCommand;
	private Value myValue;
	
	public CommandNode(String name, Command c) {
		super(name);
		myCommand = c;
		myValue = myCommand.getValue();
	}

	public Command getCommand(){
		return myCommand;
	}
	
	public Value getValue(){
		return myValue;
	}
	
	@Override
	public void parse(Scope e) throws Exception{
		getCommand().setParser(e); // Get "environment" for the ControlCommand to affect

		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: getChildren()){
			params.add(child.getValue());
			child.parse(e);
		}
		
		getCommand().setParams(params);
		

		
		if(hasParent() && (getParent() instanceof BracketNode)){
			return;
		}
		e.addCommand(getCommand());
	}
}
