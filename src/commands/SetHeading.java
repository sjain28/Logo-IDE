package commands;

import value.Value;
import turtle.Agent;

public class SetHeading extends TurtleCommand{
	
	public SetHeading(){
		setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		double firstHeading = a.getOrientation();
		Value newHeading = (Value) getParams().get(0);
		a.setOrientation(newHeading.getValue());
		double result = firstHeading - a.getOrientation();
		super.setValue(result);
		return result;
	}

}
