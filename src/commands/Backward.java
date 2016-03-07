package commands;

import parser.DoubleOptional;

public class Backward extends TurtleCommand{

	public Backward(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional distance = (DoubleOptional) getParams().get(0);
		super.getTurtle().move(-distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}

}
