package commands;

import turtle.Agent;

public class IsPenDown extends TurtleCommand{

	public IsPenDown(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent a) {
		if(a.isDown()){
			super.setValue(1);
			return 1;
		}	
		super.setValue(0);
		return 0;
	}	
}
