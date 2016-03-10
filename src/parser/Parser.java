package parser;

import turtle.Agent;
import turtle.State;
import turtle.Turtle;
import commands.Command;
import commands.ControlCommand;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.TurtleCommand;
import commands.UserDefinedFunction;
import frontend.ErrorHandler;

public class Parser {
	
	private List<String> myInputs;
	public List<ExpressionNode> myTrees;
	public List<Command> myCommands;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
	public static final ResourceBundle ENGLISH = ResourceBundle.getBundle("resources.languages/English");
	private Agent myTurtle;
	private Map<String, DoubleOptional> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
	
	CommandFactory commandFactory;
	
	public Parser(List<String> userInput, ResourceBundle language) {
		myInputs = new ArrayList<String>(userInput);
		myVariables = new HashMap<String, DoubleOptional>();
		myCommands = new ArrayList<Command>();
		commandFactory = new CommandFactory(language, this);
	}
	
	public Parser(String userInput, ResourceBundle language) {
		this(Arrays.asList(userInput.split("\\s+")), language);
//		myInputs = new ArrayList<String>(Arrays.asList(userInput.split("\\s+"))); // to be filled with parsed userInput
//		myVariables = new HashMap<String, DoubleOptional>();
//		myCommands = new ArrayList<Command>();
//		commandFactory = new CommandFactory(language, myFunctions);
	}
	
	public List<Command> parse() throws Exception{ 		
		List<String> myList = new ArrayList<String>(myInputs);
		List<ExpressionNode> expressionTrees = new ArrayList<ExpressionNode>();
		while (!myList.isEmpty()) {	
			String name = myList.remove(0);
			CommandNode head = new CommandNode(name, commandFactory.makeCommand(name));
			expressionTrees.add(assembleTree(head, myList));
		}
		
		myTrees = expressionTrees;
		
		for(ExpressionNode tree: myTrees){
			tree.parse(this);
		}
		return myCommands;
	}
	
	private ExpressionNode assembleTree(ExpressionNode head, List<String> myList) throws Exception{		
		ExpressionNode nextNode;
		if (head instanceof CommandNode) {	
			int children = ((CommandNode)head).getCommand().getNumParams();
			while(children > 0){
				nextNode = stringToNode(myList);
				head.add(assembleTree(nextNode, myList));
				children--;
			}
		}
		else if (head instanceof BracketNode) {
			nextNode = stringToNode(myList);
			while(!nextNode.getName().matches(REGEX.getString("ListEnd"))) {
				head.add(assembleTree(nextNode, myList));
				nextNode = stringToNode(myList);
			}
		}	
		return head;
	}

	private ExpressionNode stringToNode(List<String> myList) throws Exception {
		try{
			String next = myList.remove(0);
			ExpressionNode nextNode = getNode(next);
			return nextNode;
		}
		catch(Exception e){
			ErrorHandler eh = new ErrorHandler(50, 50);
			eh.init();
			eh.openError("NumParamsException");	
			throw new Exception();
		}
	}
	
	private ExpressionNode getNode(String name) throws Exception {
		if(name.matches(REGEX.getString("Constant"))){
			return new ValueNode(name);
		}
		else if(name.matches(REGEX.getString("Variable"))){
			if(!myVariables.containsKey(name)){
				myVariables.put(name, new DoubleOptional());
			}	
			return new VariableNode(name, myVariables.get(name));
		}
		else if(name.matches(REGEX.getString("ListStart")) || name.matches(REGEX.getString("ListEnd"))){
			return new BracketNode(name);
		}
		else if(name.matches(REGEX.getString("Command"))){
			return new CommandNode(name, commandFactory.makeCommand(name));
		}
		else {
			throw new Exception();
			//throw new InvalidInputException(name);
		}
	}
	
	public void setAgent(Agent turtle) { myTurtle = turtle; }
	public Agent getAgent() { return myTurtle; }
	
	public Map<String, DoubleOptional> getVariables() { return myVariables; }
	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }
	
	public void addCommand(Command c){
		myCommands.add(c);
	}

}
