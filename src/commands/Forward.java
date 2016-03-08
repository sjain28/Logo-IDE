package commands;

import parser.DoubleOptional;

public class Forward extends TurtleCommand{
	
	public Forward(){
		super.setNumParams(1);
	}
	
	
	@Override
	public double evaluate() {
		DoubleOptional distance = (DoubleOptional) super.getParams().get(0);
		super.getTurtle().move(distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}
}
