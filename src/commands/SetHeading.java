package commands;

import parser.DoubleOptional;

public class SetHeading extends TurtleCommand{
	
	public SetHeading(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		double firstHeading = getTurtle().getOrientation();
		DoubleOptional newHeading = (DoubleOptional) getParams().get(0);
		super.getTurtle().setOrientation(newHeading.getValue());
		double result = firstHeading - getTurtle().getOrientation();
		super.setValue(result);
		return result;
	}

}
