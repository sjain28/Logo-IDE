package commands;

import javafx.geometry.Point2D;
import parser.DoubleOptional;
import turtle.Agent;

public class SetTowards extends TurtleCommand{
	
	public SetTowards(){
		setNumParams(2);
	}

	@Override
	public double doCommand(Agent a) {
		DoubleOptional x = (DoubleOptional) getParams().get(0);
		DoubleOptional y = (DoubleOptional) getParams().get(1);
		
		double first = a.getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		a.setTowards(toward);
		double second = a.getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
