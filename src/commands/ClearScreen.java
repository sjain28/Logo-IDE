package commands;

import javafx.geometry.Point2D;

public class ClearScreen extends TurtleCommand{

	public ClearScreen(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		Point2D curLoc = getTurtle().getLocation();
		getTurtle().clear();
		Point2D nextLoc = getTurtle().getLocation();
		
		double dx = nextLoc.getX()-curLoc.getX();
		double dy = nextLoc.getY()-curLoc.getY();
		double result = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		super.setValue(result);
		return result;	
	}

}
