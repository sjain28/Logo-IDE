package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class SetHeading extends TurtleCommand{
	
	public SetHeading(){
		setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		double firstHeading = a.getOrientation();
		DoubleOptional newHeading = (DoubleOptional) getParams().get(0);
		a.setOrientation(newHeading.getValue());
		double result = firstHeading - a.getOrientation();
		super.setValue(result);
		return result;
	}

}
