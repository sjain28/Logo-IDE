package commands;

import javafx.geometry.Point2D;
import turtle.Agent;

public class Home extends TurtleCommand{

	public Home(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		Point2D first = a.getLocation();
		a.setLocation(new Point2D(0,0));
		double result= first.getX()*first.getX() + first.getY()*first.getY();
		super.setValue(result);
		return result;
	}

}
