package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class SetPenColor extends TurtleCommand{

	public SetPenColor(){
		setNumParams(1);
	}

	@Override
	public double doCommand(Agent t) {
		DoubleOptional indexParam = (DoubleOptional) getParams().get(0);
		int index = indexParam.getValue().intValue();
		t.setPenColor(getController().getColor(index));
		return index;
	}



}
