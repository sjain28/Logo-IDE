package commands;

import javafx.geometry.Point2D;
import value.Value;

public class SetTowards extends TurtleCommand{
	
	public SetTowards(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		Value x = (Value) getParams().get(0);
		Value y = (Value) getParams().get(1);
		
		double first = super.getTurtle().getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		getTurtle().setTowards(toward);
		double second = getTurtle().getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
