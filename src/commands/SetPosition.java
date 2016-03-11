package commands;

import javafx.geometry.Point2D;
<<<<<<< HEAD
import value.Value;
=======
import parser.DoubleOptional;
import turtle.Agent;
>>>>>>> backend

public class SetPosition extends TurtleCommand{
	
	public SetPosition(){
		setNumParams(2);
	}
	
	@Override
<<<<<<< HEAD
	public double evaluate() {
		Value x = (Value) getParams().get(0);
		Value y = (Value) getParams().get(1);
=======
	public double doCommand(Agent a) {
		DoubleOptional x = (DoubleOptional) getParams().get(0);
		DoubleOptional y = (DoubleOptional) getParams().get(1);
>>>>>>> backend
		
		Point2D nextLoc = new Point2D(x.getValue(),y.getValue());
		Point2D curLoc = a.getLocation();
		a.setLocation(nextLoc);
		
		double dx = nextLoc.getX()-curLoc.getX();
		double dy = nextLoc.getY()-curLoc.getY();
		double result = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		super.setValue(result);
		return result;
	}

}
