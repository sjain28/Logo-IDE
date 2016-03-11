package commands;

import value.Value;

public class NaturalLog extends Command{
	
	public NaturalLog(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value 	term1 = (Value) getParams().get(0);

		double result = Math.log(term1.getValue());
		super.setValue(result);
		return result;
	}

}
