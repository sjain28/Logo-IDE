package parser;
import java.util.ArrayList;
import commands.Command;

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
		getCommand().setParser(p); // cahnge to getCommand().setEnvironment()

		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: getChildren()){
			params.add(child.getValue());
			child.parse(p);
		}
		
		getCommand().setParams(params);
		getCommand().setController(p.getController());
		
		if(hasParent() && (getParent() instanceof BracketNode)){
			return;
		}
		p.addCommand(getCommand());
	}
}
