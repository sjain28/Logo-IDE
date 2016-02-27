package parser;

import java.util.List;

public class Node {
	private List<Node> children;
	private Node parent;
	
	public Node() {
		
	}
	
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	public List<Node> getChildren() { return children; }
	public Node getParent() { return parent; }
	public void setParent(Node parent) { this.parent = parent; }

	public void add(Node child) { 
		this.children.add(child); 
		child.setParent(this);
	}
	
	public boolean isLeaf() {
		return children == null;
	}
}
