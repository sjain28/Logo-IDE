package commands;

import turtle.Agent;
import value.Value;

public class Forward extends TurtleCommand{
	
	public Forward(){
		super.setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent a) {
		Value distance = (Value) super.getParams().get(0);
		a.move(distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}
}
