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
	
	public Object getValue(){
		return myValue;
	}
	
	@Override
	public List<Command> parse() throws Exception{
		List<Command> commands = new ArrayList<Command>();
		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: getChildren()){
			params.add(child.getValue());
			commands.addAll(child.parse());
		}
		
		getCommand().setParams(params);
		
		commands.add(getCommand());
		return commands;
	}
}
