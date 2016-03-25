package parser;

import commands.BlockCommand;
import commands.Command;
import commands.MakeUserInstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.UserDefinedFunction;
import frontend.DialogHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import turtle.Agent;
import turtle.Turtle;

public class Parser {
	
	public List<ExpressionNode> myTrees;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
	public static final ResourceBundle ENGLISH = ResourceBundle.getBundle("resources.languages/English");
	private CommandFactory commandFactory;
	private Scope globalEnvironment;
	
	public Parser(ResourceBundle language) {
		commandFactory = new CommandFactory(language);
		globalEnvironment = new Scope(0);
		Agent initialTurtle = new Turtle(0, new Point2D(0, 0), true, true, Color.BLUE, 3, 0);
		initialTurtle.init();
		globalEnvironment.addTurtle(initialTurtle);
		globalEnvironment.addActiveTurtle(initialTurtle);
	}
	
	public List<Command> parse(String userInput) throws Exception {
		return parse(Arrays.asList(userInput.split("\\s+")));

	}
	public List<Command> parse(List<String> userInput) throws Exception{ 	
		List<Command> myCommands = new ArrayList<Command>();
		
		List<String> myList = new ArrayList<String>(userInput);
		List<ExpressionNode> expressionTrees = new ArrayList<ExpressionNode>();
		while (!myList.isEmpty()) {	
			ExpressionNode head = stringToNode(myList, globalEnvironment);
			expressionTrees.add(assembleTree(head, myList));
		}
		myTrees = expressionTrees;
		
		for(ExpressionNode tree: myTrees){
			myCommands.addAll(tree.parse());
		}
		return myCommands;
	}
	
	private ExpressionNode assembleTree(ExpressionNode head, List<String> myList) throws Exception{		
		ExpressionNode nextNode;
		if (head instanceof CommandNode) {
			CommandNode commandNode = (CommandNode) head;
			int children = commandNode.getCommand().getNumParams();
			while(children > 0){
				nextNode = stringToNode(myList, head.getEnvironment());
				ExpressionNode child = assembleTree(nextNode, myList);
				if (commandNode.getCommand() instanceof MakeUserInstruction && children == commandNode.getCommand().getNumParams()) {
					head.getEnvironment().getFunction( ((MakeUserInstruction)commandNode.getCommand()).getFunctionName() ).setNumParams( ((BracketNode) child).getNumChildren() );
				}
				commandNode.add(child);
				children--;
			}
		}
		else if (head instanceof BracketNode) {
			nextNode = stringToNode(myList, head.getEnvironment());
			while(!nextNode.getName().matches(REGEX.getString("ListEnd"))) {
				head.add(assembleTree(nextNode, myList));
				nextNode = stringToNode(myList, head.getEnvironment());
			}
		}	
		return head;
	}

	private ExpressionNode stringToNode(List<String> myList, Environment env) throws Exception {
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
			DialogHandler eh = new DialogHandler(50, 50);
			eh.init();
			eh.openPopup("NumParamsException");	
			throw new Exception();
		}
	}
	
	private ExpressionNode getNode(String name, Environment env) throws Exception {
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
			result = new CommandNode(name, commandFactory.makeCommand(name, env));
			if ( ((CommandNode) result).getCommand() instanceof BlockCommand ) {
				env = env.makeChild(); // BlockCommands make a subenvironment
			}
		}
		else {
			throw new Exception();
		}
		result.setEnvironment(env);
		return result;
	}
	
	public Map<String, Double> getVariables() { return globalEnvironment.getVariables(); }
	public Map<String, UserDefinedFunction> getFunctions() { return globalEnvironment.getUserDefinedFunctions(); }

	public Agent getTurtle() { return globalEnvironment.getActiveTurtles().get(0); }
	
	public List<Agent> getAllTurtles(){
		return globalEnvironment.getTurtles();
	}
	
	public List<Agent> getActiveTurtles(){
		return globalEnvironment.getActiveTurtles();
	}
	
	public Environment getGlobalEnvironment() {
		return globalEnvironment;
	}


}
