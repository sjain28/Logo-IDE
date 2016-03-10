package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import commands.Command;
import commands.UserDefinedFunction;
import control.Controller;
import frontend.ErrorHandler;
import turtle.Agent;

public class Parser {
	
	private List<String> myInputs;
	private List<ExpressionNode> myTrees;
	private List<Command> myCommands;
	private Map<String, DoubleOptional> myVariables; 
	private Map<String, UserDefinedFunction> myFunctions;
	private List<Agent> allTurtles = new ArrayList<>();
	private List<Agent> activeTurtles = new ArrayList<>();
	private Controller myController;
	private CommandFactory commandFactory;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
	
	public Parser(List<String> userInput, ResourceBundle language, Controller c) {
		myInputs = new ArrayList<String>(userInput);
		myVariables = new HashMap<String, DoubleOptional>();
		myCommands = new ArrayList<Command>();
		commandFactory = new CommandFactory(language, this);
		myController = c;
	}
	
	public Parser(String userInput, ResourceBundle language, Controller c) {
		this(Arrays.asList(userInput.split("\\s+")), language, c);
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
		
	public Map<String, DoubleOptional> getVariables() { return myVariables; }
	public Map<String, UserDefinedFunction> getFunctions() { return myFunctions; }
	public void addFunction(String functionName, UserDefinedFunction function) { myFunctions.put(functionName, function); }
	
	public void addCommand(Command c){
		myCommands.add(c);
	}
	
	public List<Agent> getAllTurtles(){
		return allTurtles;
	}
	
	public List<Agent> getActiveTurtles(){
		return activeTurtles;
	}
	
	public void addTurtle(Agent t){
		allTurtles.add(t);
	}
	
	public void addActive(int index){
		Agent a = allTurtles.get(index);
		if(!activeTurtles.contains(a)){
			activeTurtles.add(a);
		}
	}
	
	protected Controller getController(){
		return myController;
	}
}
