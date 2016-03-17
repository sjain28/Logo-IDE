package parser;

import commands.BlockCommand;
import commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.UserDefinedFunction;
import control.Controller;
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
			String name = myList.remove(0);
			CommandNode head = new CommandNode(name, commandFactory.makeCommand(name, globalEnvironment));
			head.setEnvironment(globalEnvironment);
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
		Environment curEnv = head.getEnvironment();

		if (head instanceof CommandNode) {
			CommandNode commandNode = (CommandNode) head;
			if ( commandNode.getCommand() instanceof BlockCommand ) {
				commandNode.setEnvironment(curEnv.makeChild()); // BlockCommands make a subenvironment
			}
			int children = commandNode.getCommand().getNumParams();
			while(children > 0){
				nextNode = stringToNode(myList, curEnv);
				commandNode.add(assembleTree(nextNode, myList));
				children--;
			}
		}
		else if (head instanceof BracketNode) {
			nextNode = stringToNode(myList, curEnv);
			while(!nextNode.getName().matches(REGEX.getString("ListEnd"))) {
				head.add(assembleTree(nextNode, myList));
				nextNode = stringToNode(myList, curEnv);
			}
		}	
		return head;
	}

	private ExpressionNode stringToNode(List<String> myList, Environment env) throws Exception {
		try{
			String next = myList.remove(0);
			ExpressionNode nextNode = getNode(next, env);
			//if (nextNode instanceof CommandNode && ((CommandNode)nextNode).getCommand() instanceof MakeUserInstruction ) { }
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
		}
		else {
			throw new Exception();
		}
		result.setEnvironment(env);
		return result;
	}
	
	
	public Map<String, Double> getVariables() { return globalEnvironment.getVariables(); }
//	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
//	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }

	public Agent getTurtle() { return globalEnvironment.getActiveTurtles().get(0); } // TODO: Delete this method, implement multiple agents
	
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
