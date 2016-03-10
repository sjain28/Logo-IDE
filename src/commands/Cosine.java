package commands;

import value.Value;

public class Cosine extends Command{
	
	public Cosine(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = Math.cos(term1.getValue());
		super.setValue(result);
		return result;
	}

}
