package parser;

import commands.CommandFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Parser {

	private List<String> myInputs;
	private List<ExpressionNode> myTrees;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources/syntax");
	public static final ResourceBundle ENGLISH = ResourceBundle.getBundle("resources/English");
	
	private Map<String, DoubleProperty> variables; 
	
	CommandFactory comFac = new CommandFactory(ENGLISH);
	
	public Parser(List<String> in) {
		myInputs = in;
		variables = new HashMap<String, DoubleProperty>();
	}
	
	protected void parse(){
		
	}
	
	protected ExpressionNode assembleTree(ExpressionNode head, int numParams){
		
		int children = 0;
		
		while(children < numParams){
			String next = myInputs.remove(0);
			
			if(next.matches(REGEX.getString("Constant"))){
				head.add(new ValueNode(next));
				children ++;
			}
			else if(next.matches(REGEX.getString("Variable"))){
				variables.put(next, null);
				head.add(new VariableNode(next, variables.get(next))); //need to figure out how to deal with variables
			}
			else if(next.equals("[")){
				BracketNode temp = new BracketNode(next);
				next = myInputs.remove(0);
				while(!next.equals("]")){
					-
				}
			}
			else{
				head.add(new CommandNode(next)); //need to figure out how to deal with brackets
												//need to do this using resource bundle
			}	
		}
		return null;
	}

}
