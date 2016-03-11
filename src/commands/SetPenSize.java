package commands;

import turtle.Agent;
import value.Value;

public class SetPenSize extends TurtleCommand{
	
	public SetPenSize(){
		setNumParams(1);
	}

	@Override
	public double doCommand(Agent t) {
		Value nextWidth = (Value) super.getParams().get(0);
		t.setLineWidth(nextWidth.getValue());
		setValue(nextWidth.getValue());
		return nextWidth.getValue();
	}

}
