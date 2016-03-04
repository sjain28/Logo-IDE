package commands;

import parser.DoubleOptional;

public class Cosine extends Command{
	
	public Cosine(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = Math.cos(term1.getValue());
		super.setValue(result);
		return result;
	}

}
