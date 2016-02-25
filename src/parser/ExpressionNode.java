package parser;

import java.util.List;

public class ExpressionNode {
	private String value;
	private List<ExpressionNode> children;
	private ExpressionNode parent;
	
	public ExpressionNode(String val) {
		value = val;
	}
	
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	public List<ExpressionNode> getChildren() { return children; }
	public ExpressionNode getParent() { return parent; }
	public void setParent(ExpressionNode parent) { this.parent = parent; }

	public void add(ExpressionNode child) { 
		this.children.add(child); 
		child.setParent(this);
	}
}
