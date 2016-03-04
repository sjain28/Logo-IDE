package commands;

import javafx.geometry.Point2D;
import parser.DoubleOptional;

public class SetTowards extends TurtleCommand{
	
	public SetTowards(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		DoubleOptional x = (DoubleOptional) getParams().get(0);
		DoubleOptional y = (DoubleOptional) getParams().get(1);
		
		double first = super.getTurtle().getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		getTurtle().setTowards(toward);
		double second = getTurtle().getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
