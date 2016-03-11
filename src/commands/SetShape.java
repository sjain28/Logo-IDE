package commands;

import turtle.Agent;
import value.Value;

public class SetShape extends TurtleCommand{
	
	public SetShape(){
		setNumParams(1);
	}
	@Override
	public double doCommand(Agent t) {
		Value indexParam = (Value) getParams().get(0);
		int index = indexParam.getValue().intValue();
		t.setShape(index);
		setValue(index);
		return index;
	}

}
