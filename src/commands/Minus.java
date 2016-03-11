package commands;

import value.Value;

public class Minus extends Command{
	
	public Minus(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = -term1.getValue();
		super.setValue(result);
		return result;
	}

}
