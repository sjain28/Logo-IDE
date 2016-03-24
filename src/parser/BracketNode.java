//This entire file is part of my masterpiece.
//Saumya Jain
/**
 * This class is good design because it reflects the principle of active classes.
 * During refactoring I added the assemble() method and the parse() method. 
 * Previously, these methods were inside if/else blocks in the Parser class. 
 * Because these methods are unique to different types of Nodes, it made sense to move that logic into the BracketNode class, 
 * instead of using a conditional block in the Parser class. 
 * Now, the conditional block has been eliminated and the BracketNode class handles more of its internal logic.  
 */

package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import commands.Command;

public class BracketNode extends ExpressionNode{
	public static final ResourceBundle REGEX = ResourceBundle.getBundle("resources.languages/Syntax");

	private List<Object> bracketContents;
	
	public BracketNode(String val) {
		super(val);
		bracketContents = new ArrayList<Object>();
	}

	@Override
	protected Object getValue() {
		return (List<Object>) bracketContents;
	}
	
	public int getNumChildren() {
		return getChildren().size();
	}
	
	public void addElement(Object element){
		bracketContents.add(element);
	}
	
	@Override
	protected List<Command> parse() throws Exception{
		for(ExpressionNode child: getChildren()){
			if(child instanceof CommandNode){
				List<Command> commands = child.parse();
				for(Command c : commands) {
					addElement(c);
				}
			}else{
				addElement(child.getValue());
				child.parse();
			}
		}
		return new ArrayList<Command>();
	}

	@Override
	protected void assemble(List<String> input, Parser p) throws Exception {
		ExpressionNode nextNode;	
		nextNode = p.stringToNode(input, getEnvironment());
		while(!nextNode.getName().matches(REGEX.getString("ListEnd"))) {
			add(p.assembleTree(nextNode, input));
			nextNode = p.stringToNode(input, getEnvironment());
		}
	}
}
