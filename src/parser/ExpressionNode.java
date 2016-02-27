package parser;

import java.util.List;

public abstract class ExpressionNode { 
	
	private String name; // String that was read in
	private List<ExpressionNode> children;
	private ExpressionNode parent;
	
	
	public ExpressionNode(String val) {
		name = val;
	}
	
	public String getName() { return name; }
	public List<ExpressionNode> getChildren() { return children; }
	public ExpressionNode getParent() { return parent; }
	public void setParent(ExpressionNode parent) { this.parent = parent; }

	public void add(ExpressionNode child) { 
		this.children.add(child); 
		child.setParent(this);
	}
	
	public abstract Object getValue();
}
