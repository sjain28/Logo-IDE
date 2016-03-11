package commands;

import value.Value;

public class Sine extends Command{
	
	public Sine(){
		setNumParams(1);
	}

	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = Math.sin(term1.getValue());
		super.setValue(result);
		return result;
	}

}
