package commands;

import javafx.geometry.Point2D;
import turtle.Agent;
import value.Value;

public class SetTowards extends TurtleCommand{
	
	public SetTowards(){
		setNumParams(2);
	}

	@Override
	public double doCommand(Agent a) {
		Value x = (Value) getParams().get(0);
		Value y = (Value) getParams().get(1);
		
		double first = a.getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		a.setTowards(toward);
		double second = a.getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
