package commands;

import turtle.Agent;
import value.Value;

public class SetPenColor extends TurtleCommand{

	public SetPenColor(){
		setNumParams(1);
	}

	@Override
	public double doCommand(Agent t) {
		Value indexParam = (Value) getParams().get(0);
		int index = indexParam.getValue().intValue();
		t.setPenColor(getController().getColor(index));
		return index;
	}



}
