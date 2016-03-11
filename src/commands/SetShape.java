package commands;

import parser.DoubleOptional;
import turtle.Agent;

public class SetShape extends TurtleCommand{
	
	public SetShape(){
		setNumParams(1);
	}
	@Override
	public double doCommand(Agent t) {
		DoubleOptional indexParam = (DoubleOptional) getParams().get(0);
		int index = indexParam.getValue().intValue();
		t.setShape(index);
		setValue(index);
		return index;
	}

}
