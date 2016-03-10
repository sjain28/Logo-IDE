package commands;

import turtle.Agent;

public class IsShowing extends TurtleCommand{

	public IsShowing(){
		setNumParams(0);
	}

	@Override
	public double doCommand(Agent a) {
		if(a.isVisible()){
			super.setValue(1);
			return 1;
		}
		super.setValue(0);
		return 0;
	}

}
