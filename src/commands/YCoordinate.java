package commands;

import turtle.Agent;

public class YCoordinate extends TurtleCommand{

	public YCoordinate(){
		setNumParams(0);
	}

	@Override
	public double doCommand(Agent a) {
		super.setValue(a.getLocation().getY());
		return a.getLocation().getY();
	}

}
