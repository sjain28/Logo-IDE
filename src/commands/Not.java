package commands;

import value.Value;

public class Not extends Command{
	
	public Not(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		int result;
		Value term1 = (Value) getParams().get(0);

		if(term1.getValue().equals(0))
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
