package commands;

import turtle.Agent;

public class PenDown extends TurtleCommand{

	public PenDown(){
		setNumParams(0);
	}
	@Override
	public double doCommand(Agent a) {
		a.changePenVisibility(true);
		super.setValue(1);
		return 1;
	}

}
