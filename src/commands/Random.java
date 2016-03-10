package commands;

import value.Value;

public class Random extends Command{
	
	public Random(){
		setNumParams(1);
	}

	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = Math.random()*term1.getValue();
		super.setValue(result);
		return result;
	}

}
