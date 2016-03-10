package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class Backward extends TurtleCommand{

	public Backward(){
		setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		DoubleOptional distance = (DoubleOptional) getParams().get(0);
		a.move(-distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}

}
