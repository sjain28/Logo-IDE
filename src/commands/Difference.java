package commands;

import parser.DoubleOptional;

public class Difference extends Command{

	public Difference(){
		setNumParams(2);
	}
	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);
		
		double result = term1.getValue()-term2.getValue();
		super.setValue(result);
		return result;

	}


}
