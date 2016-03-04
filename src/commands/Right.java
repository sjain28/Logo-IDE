package commands;

import parser.DoubleOptional;

public class Right extends TurtleCommand{
	
	public Right(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional rotation = (DoubleOptional) getParams().get(0);
		super.getTurtle().turn(rotation.getValue()); //default is clockwise
		super.setValue(rotation.getValue());
		return rotation.getValue();
	}

}
