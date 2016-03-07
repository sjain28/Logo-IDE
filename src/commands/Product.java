package commands;

import parser.DoubleOptional;

public class Product extends Command{

	public Product(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional first = (DoubleOptional) getParams().get(0);
		DoubleOptional second = (DoubleOptional) getParams().get(1);
		double result = first.getValue()*second.getValue();
		super.setValue(result);
		return result;
	}

}
