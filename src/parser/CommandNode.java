package parser;
import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.ControlCommand;
import commands.MakeUserInstruction;
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
	
	protected Object getValue(){
		return myValue;
	}
	
	@Override 
	public void setEnvironment(Environment newEnv) {
		super.setEnvironment(newEnv);
		myCommand.setEnvironment(newEnv);
	}
	
	@Override
	protected List<Command> parse() throws Exception{
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

	@Override
	protected void assemble(List<String> input, Parser p) throws Exception{
		ExpressionNode nextNode; 
		int children = getCommand().getNumParams();
		while(children > 0){
			nextNode = p.stringToNode(input, getEnvironment());
			ExpressionNode child = p.assembleTree(nextNode, input);
			if (getCommand() instanceof MakeUserInstruction && children == getCommand().getNumParams()) {
				getEnvironment().getFunction( ((MakeUserInstruction) getCommand()).getFunctionName() ).setNumParams( ((BracketNode) child).getNumChildren() );
			}
			add(child);
			children--;
		}
	}
}

