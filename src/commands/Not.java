package commands;

import value.DoubleOptional;

public class Not extends Command{
	
	public Not(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		int result;
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);

		if(term1.getValue().equals(0))
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
