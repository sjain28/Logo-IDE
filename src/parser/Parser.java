package parser;

import turtle.State;
import turtle.Turtle;
import commands.Command;
import commands.CommandFactory;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.TurtleCommand;
public class Parser {
	private List<String> myInputs;
	public List<ExpressionNode> myTrees;
	public List<Command> myCommands;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");
	public static final ResourceBundle ENGLISH = ResourceBundle.getBundle("resources.languages/English");
	private Map<String, DoubleOptional> variables; 
	
	CommandFactory commandFactory = new CommandFactory(ENGLISH);
	
	public Parser(List<String> userInput) {
		myInputs = new ArrayList<String>(userInput);
		variables = new HashMap<String, DoubleOptional>();
		myCommands = new ArrayList<Command>();
	}
	
	public Parser(String userInput) {
		myInputs = new ArrayList<String>(Arrays.asList(userInput.split("\\s+"))); // to be filled with parsed userInput
		variables = new HashMap<String, DoubleOptional>();
		myCommands = new ArrayList<Command>();
	}
	
	public List<ExpressionNode> parse() throws Exception{ 			//Creates expression trees
		List<String> myList = new ArrayList<String>(myInputs);
		List<ExpressionNode> expressionTrees = new ArrayList<ExpressionNode>();
		while (!myList.isEmpty()) {	
			String name = myList.remove(0);
			CommandNode head = new CommandNode(name, commandFactory.makeCommand(name));
			expressionTrees.add(assembleTree(head, myList));
		}
		myTrees = expressionTrees;
		
		for(ExpressionNode tree: myTrees){
			parseTree((CommandNode) tree);
		}
		return null;
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
		String next = myList.remove(0);
		ExpressionNode nextNode = getNode(next);
		return nextNode;
	}
	
	private void parseTree(CommandNode head) throws Exception{ //we assume head is a command node
		ArrayList<Object> params = new ArrayList<Object>();
		for(ExpressionNode child: head.getChildren()){
			params.add(child.getValue());
			if(child instanceof CommandNode){
				parseTree((CommandNode) child);
			}
			if(child instanceof BracketNode){
				parseBracket((BracketNode) child);
			}
		}
		head.getCommand().setParams(params); //Bind the current command object to the value of the child node
		myCommands.add(head.getCommand());
	}
	
	private void parseBracket(BracketNode node) throws Exception{ //Can probably refactor this method and the one above
		for(ExpressionNode child: node.getChildren()){
			node.addElement(child.getValue());
			if(child instanceof CommandNode){
				parseTree((CommandNode) child);
			}
			if(child instanceof BracketNode){
				parseBracket((BracketNode)child);
			}
		}
	}
	
	private ExpressionNode getNode(String name) throws Exception {
		if(name.matches(REGEX.getString("Constant"))){
			return new ValueNode(name);
		}
		else if(name.matches(REGEX.getString("Variable"))){
			variables.put(name, new DoubleOptional());
			return new VariableNode(name, variables.get(name));
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
	
	public static void main (String[] args) throws Exception {
		String[] input = {"rt", "45","fD", "fd", "fd", "fd","+", "*", "2","3", "*", "5", "Sin","/","PI", "2"};
		Parser p = new Parser(Arrays.asList(input));
		p.parse();
		Turtle temp = new Turtle(0, new Point2D(0,0),true, true, null, 1, 0);
		
		for(Command c: p.myCommands){
			if(c instanceof TurtleCommand){
				TurtleCommand d = (TurtleCommand) c;
				d.setTurtle(temp);
				d.evaluate();
			}else{
				c.evaluate();
			}
		}
		
		for(State s: temp.getStates()){
			System.out.println(s.getLocation() + "  " + s.getOrientation());
		}			
	}
}
