package commands;

import value.DoubleOptional;

public class Sine extends Command{
	
	public Sine(){
		setNumParams(1);
	}

	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = Math.sin(term1.getValue());
		super.setValue(result);
		return result;
	}

}
