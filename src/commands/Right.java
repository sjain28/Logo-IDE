package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class Right extends TurtleCommand{
	
	public Right(){
		setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		DoubleOptional rotation = (DoubleOptional) getParams().get(0);
		a.turn(rotation.getValue()); //default is clockwise
		super.setValue(rotation.getValue());
		return rotation.getValue();
	}

}
