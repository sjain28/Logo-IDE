package commands;

import javafx.geometry.Point2D;
<<<<<<< HEAD
import value.Value;
=======
import parser.DoubleOptional;
import turtle.Agent;
>>>>>>> backend

public class SetTowards extends TurtleCommand{
	
	public SetTowards(){
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
		
		double first = a.getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		a.setTowards(toward);
		double second = a.getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
