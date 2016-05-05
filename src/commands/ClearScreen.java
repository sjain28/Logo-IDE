package commands;

import javafx.geometry.Point2D;
import turtle.Agent;

public class ClearScreen extends TurtleCommand{

	public ClearScreen(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		Point2D curLoc = a.getLocation();
		a.clear();
		Point2D nextLoc = a.getLocation();
		
		double dx = nextLoc.getX()-curLoc.getX();
		double dy = nextLoc.getY()-curLoc.getY();
		double result = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		super.setValue(result);
		return result;
	}

}
