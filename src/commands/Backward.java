package commands;

import value.Value;

public class Backward extends TurtleCommand{

	public Backward(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value distance = (Value) getParams().get(0);
		super.getTurtle().move(-distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}

}
