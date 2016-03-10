package commands;

import turtle.Agent;

public class HideTurtle extends TurtleCommand{

	public HideTurtle(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		a.changeVisibility(false);
		super.setValue(0);
		return 0;
	}

}
