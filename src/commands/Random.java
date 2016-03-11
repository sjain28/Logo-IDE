package commands;

import value.DoubleOptional;

public class Random extends Command{
	
	public Random(){
		setNumParams(1);
	}

	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = Math.random()*term1.getValue();
		super.setValue(result);
		return result;
	}

}
