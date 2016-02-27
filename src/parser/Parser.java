package parser;

import java.util.List;
import java.util.ResourceBundle;

public class Parser {

	private List<String> myInputs;
	private List<ExpressionNode> myTrees;
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources/syntax");
	
	public Parser(List<String> in) {
		myInputs = in;
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
			else
				
		}
		
		
		return null;
	}

}
