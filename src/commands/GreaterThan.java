package commands;

import parser.DoubleOptional;

public class GreaterThan extends Command{

	public GreaterThan(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		int result;
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);
		
		if(term1.getValue()>term2.getValue())
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
