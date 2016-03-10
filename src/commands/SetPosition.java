package commands;

import javafx.geometry.Point2D;
import value.Value;

public class SetPosition extends TurtleCommand{
	
	public SetPosition(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		Value x = (Value) getParams().get(0);
		Value y = (Value) getParams().get(1);
		
		Point2D nextLoc = new Point2D(x.getValue(),y.getValue());
		Point2D curLoc = getTurtle().getLocation();
		getTurtle().setLocation(nextLoc);
		
		double dx = nextLoc.getX()-curLoc.getX();
		double dy = nextLoc.getY()-curLoc.getY();
		double result = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		super.setValue(result);
		return result;
	}

}
