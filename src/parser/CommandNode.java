package parser;
import java.util.ArrayList;
import commands.Command;
import commands.ControlCommand;
import commands.TurtleCommand;
import value.DoubleOptional;


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
	
	public Object getValue(){
		return myValue;
	}
	
<<<<<<< HEAD
	@Override
	public List<Command> parse() throws Exception{
		List<Command> commands = new ArrayList<Command>();
=======
	public void parse(Parser p) throws Exception{
		getCommand().setParser(p); // cahnge to getCommand().setEnvironment()

>>>>>>> backend
		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: getChildren()){
			params.add(child.getValue());
			commands.addAll(child.parse());
		}
		
		getCommand().setParams(params);
<<<<<<< HEAD
		
		commands.add(getCommand());
		return commands;
=======
		getCommand().setController(p.getController());
		
		if(hasParent() && (getParent() instanceof BracketNode)){
			return;
		}
		p.addCommand(getCommand());
>>>>>>> backend
	}
}
