package commands;

import value.Value;

public class Forward extends TurtleCommand{
	
	public Forward(){
		super.setNumParams(1);
	}
	
	
	@Override
	public double evaluate() {
		Value distance = (Value) super.getParams().get(0);
		super.getTurtle().move(distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}
}
