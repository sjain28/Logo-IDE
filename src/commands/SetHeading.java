package commands;

import value.Value;

public class SetHeading extends TurtleCommand{
	
	public SetHeading(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		double firstHeading = getTurtle().getOrientation();
		Value newHeading = (Value) getParams().get(0);
		super.getTurtle().setOrientation(newHeading.getValue());
		double result = firstHeading - getTurtle().getOrientation();
		super.setValue(result);
		return result;
	}

}
