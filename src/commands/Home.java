package commands;

import java.util.List;

import javafx.geometry.Point2D;

public class Home extends TurtleCommand{

	@Override
	public int getNumParams() {
		return 0;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		return;
		
	}

	@Override
	public double evaluate() {
		Point2D first = getTurtle().getLocation();
		getTurtle().setLocation(new Point2D(0,0));
		return first.getX()*first.getX() + first.getY()*first.getY();
	}

}
