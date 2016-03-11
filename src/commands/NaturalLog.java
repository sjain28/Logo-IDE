package commands;

import value.DoubleOptional;

public class NaturalLog extends Command{
	
	public NaturalLog(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional 	term1 = (DoubleOptional) getParams().get(0);

		double result = Math.log(term1.getValue());
		super.setValue(result);
		return result;
	}

}
