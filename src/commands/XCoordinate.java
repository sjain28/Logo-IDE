package commands;

import turtle.Agent;

public class XCoordinate extends TurtleCommand{

	public XCoordinate(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		super.setValue(a.getLocation().getX());
		return a.getLocation().getX();
	}

}
