package commands;

import turtle.Agent;

public class ShowTurtle extends TurtleCommand{

	public ShowTurtle(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		a.changeVisibility(true);
		super.setValue(1);
		return 1;
	}

}
