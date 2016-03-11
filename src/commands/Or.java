package commands;

import value.Value;

public class Or extends Command{

	public Or(){
		setNumParams(2);
	}

	@Override
	public double evaluate() {
		int result;
		Value term1 = (Value) getParams().get(0);
		Value term2 = (Value) getParams().get(1);
		if(term1.getValue().equals(0.0) && term2.getValue().equals(0.0))
			result = 0;
		else
			result = 1;
		
		super.setValue(result);
		return result;
	}

}
