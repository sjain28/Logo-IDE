package commands;

import value.DoubleOptional;

public class NotEqual extends Command{
	
	public NotEqual(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		int result;
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		DoubleOptional term2 = (DoubleOptional) getParams().get(1);

		if(!term1.getValue().equals(term2.getValue()))
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
