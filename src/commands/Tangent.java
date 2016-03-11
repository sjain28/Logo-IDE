package commands;

import value.DoubleOptional;

public class Tangent extends Command{
	
	public Tangent(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = Math.tan(term1.getValue());
		super.setValue(result);
		return result;
	}

}
