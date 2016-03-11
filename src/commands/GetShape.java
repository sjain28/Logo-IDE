package commands;

import turtle.Agent;

public class GetShape extends TurtleCommand{
	
	public GetShape(){
		setNumParams(0);
	}

	@Override
	public double doCommand(Agent t) {
		setValue(t.getShape());
		return t.getShape();
	}

}
