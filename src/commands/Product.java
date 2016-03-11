package commands;

import value.Value;

public class Product extends Command{

	public Product(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		Value first = (Value) getParams().get(0);
		Value second = (Value) getParams().get(1);
		double result = first.getValue()*second.getValue();
		super.setValue(result);
		return result;
	}

}
