//This entire file is part of my masterpiece.
//Saumya Jain
/**
 * This class reflects good design because it uses the factory design pattern. Formerly this long block was in parser. I realized that 
 * since its only purpose is to create an object based on input, it's an ideal application of the factory pattern.
 */
package parser;

import java.util.ResourceBundle;

import commands.BlockCommand;

public class NodeFactory {
	public static final ResourceBundle REGEX = Parser.REGEX;

	protected ExpressionNode getNode(String name, Environment env, CommandFactory fac) throws Exception{
		ExpressionNode result;
		if(name.matches(REGEX.getString("Constant"))){
			result = new NumberNode(name);
		}
		else if(name.matches(REGEX.getString("Variable"))){	
			result = new VariableNode(name);
		}
		else if(name.matches(REGEX.getString("ListStart")) || name.matches(REGEX.getString("ListEnd"))){
			result = new BracketNode(name);
		}
		else if(name.matches(REGEX.getString("Command"))){
			result = new CommandNode(name, fac.makeCommand(name, env));
			if ( ((CommandNode) result).getCommand() instanceof BlockCommand ) {
				env = env.makeChild();
			}
		}
		else {
			throw new Exception();
		}
		
		result.setEnvironment(env);
		return result;
	}

}
