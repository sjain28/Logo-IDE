package commands;

import value.DoubleOptional;

public class Power extends Command{
	
	public Power(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);
		double result = Math.pow(term1.getValue(), term2.getValue());
		super.setValue(result);
		return result;
	}

}
