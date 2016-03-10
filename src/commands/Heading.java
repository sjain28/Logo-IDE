package commands;

import turtle.Agent;

public class Heading extends TurtleCommand{

	public Heading(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		super.setValue(a.getOrientation());
		return a.getOrientation();
	}

}
