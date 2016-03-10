package commands;

import javafx.geometry.Point2D;
import parser.DoubleOptional;
import turtle.Agent;

public class SetPosition extends TurtleCommand{
	
	public SetPosition(){
		setNumParams(2);
	}
	
	@Override
	public double doCommand(Agent a) {
		DoubleOptional x = (DoubleOptional) getParams().get(0);
		DoubleOptional y = (DoubleOptional) getParams().get(1);
		
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
