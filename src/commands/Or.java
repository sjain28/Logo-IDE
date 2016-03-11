package commands;

import value.DoubleOptional;

public class Or extends Command{

	public Or(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		int result;
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);
		if(term1.getValue().equals(0.0) && term2.getValue().equals(0.0))
			result = 0;
		else
			result = 1;
		
		super.setValue(result);
		return result;
	}

}
