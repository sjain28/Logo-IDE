package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class Forward extends TurtleCommand{
	
	public Forward(){
		super.setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		DoubleOptional distance = (DoubleOptional) super.getParams().get(0);
		a.move(distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}
}
