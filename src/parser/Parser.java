package parser;

import commands.Command;
import commands.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Parser {
	private List<String> myInputs;
	private List<ExpressionNode> myTrees;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources/syntax");
	public static final ResourceBundle ENGLISH = ResourceBundle.getBundle("resources/English");
	private Map<String, DoubleOptional> variables; 
	
	CommandFactory commandFactory = new CommandFactory(ENGLISH);
	
	public Parser(List<String> userInput) {
		myInputs = new ArrayList<String>(userInput);
		variables = new HashMap<String, DoubleOptional>();
	}
	
	public Parser(String userInput) {
		myInputs = new ArrayList<String>(Arrays.asList(userInput.split("\\s+"))); // to be filled with parsed userInput
		variables = new HashMap<String, DoubleOptional>();
	}
	
	public List<Command> parse() throws Exception{
		List<String> myList = new ArrayList<String>(myInputs);
		List<ExpressionNode> expressionTrees = new ArrayList<ExpressionNode>();
		while (!myList.isEmpty()) {	
			String name = myList.remove(0);
			CommandNode head = new CommandNode(name, commandFactory.makeCommand(name));
			expressionTrees.add(assembleTree(head, myList));
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
	
//	public static void main (String[] args) throws Exception {
//		String[] input = {"fd", "[", "rt", "90", "]", "MAKE", ":x"};
//		Parser p = new Parser(Arrays.asList(input));
//		p.parse();
//	}

}
