package commands;

import turtle.Agent;

public class PenUp extends TurtleCommand{
	
	public PenUp(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		a.changePenVisibility(false);
		super.setValue(0);
		return 0;
	}

}
