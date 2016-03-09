package commands;

import parser.DoubleOptional;

public class Minus extends Command{
	
	public Minus(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = -term1.getValue();
		super.setValue(result);
		return result;
	}

}
