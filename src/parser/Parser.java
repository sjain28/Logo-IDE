//This entire file is part of my masterpiece.
//Saumya Jain
/**
 * I wrote most of this class, and I refactored it heavily after the second sprint. 
 * This class follows conventions for variable names and variable/method visibility
 * During refactoring I removed switchcases in several locations: 
 *  The if/else block in assembleTree was removed, and that logic was placed in BracketNode and CommandNode
 *  The if/else block in stringToNode was moved into the nodeFactory class, which I created during refactoring
 *  Parameter initialization was moved from assembleTree into the CommandNode and BracketNode classes
 *  Created throwError() method to eliminate code duplication
 */

package parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.Command;
import commands.MakeUserInstruction;
import commands.UserDefinedFunction;
import frontend.DialogHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import turtle.Agent;
import turtle.Turtle;

public class Parser {
	
	public List<ExpressionNode> myTrees;
	private CommandFactory commandFactory;
	private Scope globalEnvironment;
	private NodeFactory nodeFac;
	
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
	private final Agent initialTurtle = new Turtle(0, new Point2D(0, 0), true, true, Color.BLUE, 3, 0);
	private final int ERRORSIZE = 50;
	
	public Parser(ResourceBundle language) {
		commandFactory = new CommandFactory(language);
		globalEnvironment = new Scope(0);
		initialTurtle.init();
		globalEnvironment.addTurtle(initialTurtle);
		globalEnvironment.addActiveTurtle(initialTurtle);
		nodeFac = new NodeFactory();
	}
	
	public List<Command> parse(String userInput) throws Exception {
		return parse(Arrays.asList(userInput.split("\\s+")));
	}
	
	public List<Command> parse(List<String> userInput) throws Exception{ 	
		List<Command> myCommands = new ArrayList<Command>();
		List<String> inputList = new ArrayList<String>(userInput);
		List<ExpressionNode> expressionTrees = new ArrayList<ExpressionNode>();
		
		while (!inputList.isEmpty()) {	
			ExpressionNode head = stringToNode(inputList, globalEnvironment);
			expressionTrees.add(assembleTree(head, inputList));
		}
		myTrees = expressionTrees;
		for(ExpressionNode tree: myTrees){
			myCommands.addAll(tree.parse());
		}
		return myCommands;
	}

	protected ExpressionNode assembleTree(ExpressionNode head, List<String> myList) throws Exception{		
		head.assemble(myList, this);
		return head;
	}
	
	protected ExpressionNode stringToNode(List<String> myList, Environment env) throws Exception {
		try{
			String next = myList.remove(0);
			ExpressionNode nextNode = getNode(next, env);
			if (nextNode instanceof CommandNode && ((CommandNode)nextNode).getCommand() instanceof MakeUserInstruction ) {
				String functionName = myList.remove(0);
				env.addFunction(functionName, new UserDefinedFunction(functionName));
				((MakeUserInstruction)((CommandNode)nextNode).getCommand()).setFunctionName(functionName);
			}
			return nextNode;
		}
		catch(Exception e){ 
			throwError("numParamsException");
			throw new Exception();
		}
	}
	
	private ExpressionNode getNode(String name, Environment env) throws Exception {
		try{
			return nodeFac.getNode(name, env, commandFactory);
		}
		catch(Exception e) {
			throwError("InvalidInputException");
			throw new Exception();
		}
	}
	
	private void throwError(String errorType) throws Exception{
		DialogHandler errorHandler = new DialogHandler(ERRORSIZE, ERRORSIZE);
		errorHandler.init();
		errorHandler.openPopup(errorType);	
	}
	
	//Getters and setters
	public Map<String, Double> getVariables() 				{ return globalEnvironment.getVariables(); }
	public Map<String, UserDefinedFunction> getFunctions() 	{ return globalEnvironment.getUserDefinedFunctions(); }
	public Agent getTurtle() 								{ return globalEnvironment.getActiveTurtles().get(0); }
	public List<Agent> getAllTurtles()						{ return globalEnvironment.getTurtles();	}
	public List<Agent> getActiveTurtles() 					{ return globalEnvironment.getActiveTurtles(); }
	public Environment getGlobalEnvironment() 				{ return globalEnvironment; }
}
