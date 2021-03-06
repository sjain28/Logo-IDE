package commands;

import value.Value;

public class LessThan extends Command{

	public LessThan(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		int result;
		Value term1 = (Value) getParams().get(0);
		Value term2 = (Value) getParams().get(1);
		
		if(term1.getValue()<term2.getValue())
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
