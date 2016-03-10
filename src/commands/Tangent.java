package commands;

import value.Value;

public class Tangent extends Command{
	
	public Tangent(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = Math.tan(term1.getValue());
		super.setValue(result);
		return result;
	}

}
