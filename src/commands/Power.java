package commands;

import value.Value;

public class Power extends Command{
	
	public Power(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		Value term2 = (Value) getParams().get(1);
		double result = Math.pow(term1.getValue(), term2.getValue());
		super.setValue(result);
		return result;
	}

}
