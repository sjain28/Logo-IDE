package commands;

import javafx.geometry.Point2D;

public class Home extends TurtleCommand{

	public Home(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		Point2D first = getTurtle().getLocation();
		getTurtle().setLocation(new Point2D(0,0));
		double result= first.getX()*first.getX() + first.getY()*first.getY();
		super.setValue(result);
		return result;
	}

}
