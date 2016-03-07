package commands;

import parser.DoubleOptional;

public class Quotient extends Command{
	
	public Quotient(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);
		double result = term1.getValue()/term2.getValue();
		super.setValue(result);
		return result;
	}

}
