package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class SetPenSize extends TurtleCommand{
	
	public SetPenSize(){
		setNumParams(1);
	}

	@Override
	public double doCommand(Agent t) {
		DoubleOptional nextWidth = (DoubleOptional) super.getParams().get(0);
		t.setLineWidth(nextWidth.getValue());
		setValue(nextWidth.getValue());
		return nextWidth.getValue();
	}

}
