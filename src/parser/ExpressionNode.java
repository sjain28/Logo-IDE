package parser;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import value.DoubleOptional;

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
	
	public abstract List<Command> parse() throws Exception;
	
}
