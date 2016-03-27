package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

// This class is part of my code masterpiece, along with CommandNode, BracketNode, and VariableNode, which
// show examples of the different types of subclasses of ExpressionNode.
// This code demonstrates the use of inheritance and polymorphism to have different types of ExpressionNodes,
// including CommandNodes, BracketNodes, and VariableNodes, exhibit different behavior when node.parse() is called.
// This allows you to parse a tree composed on many different types of ExpressionNodes through a single recursive method.
// The superclass also contains all methods common to all ExpressionNodes to avoid duplicated code.

public abstract class ExpressionNode { 
	
	private String name; // String that was read in
	private List<ExpressionNode> children;
	private ExpressionNode parent;
	private Environment myEnvironment;
	
	public ExpressionNode(String val) {
		children = new ArrayList<ExpressionNode>();
		name = val;
	}
	
	public String getName() 						{ return name; }
	public List<ExpressionNode> getChildren() 		{ return children; }
	public ExpressionNode getParent() 				{ return parent; }
	public void setParent(ExpressionNode parent)	{ this.parent = parent; }
	public Environment getEnvironment() 			{ return myEnvironment; }
	public void setEnvironment(Environment env) 	{ myEnvironment = env; }

	public void add(ExpressionNode child) { 
		this.children.add(child); 
		child.setParent(this);
	}
	
	public boolean hasParent(){
		return parent != null;
	}
	
	public abstract Object getValue();
	
	/** 
	 * Returns a list of commands that represents all commands from the expression tree 
	 * starting from this node down in the order they are to be executed.
	 */
	public abstract List<Command> parse() throws Exception;
	
}
