package commands;

import value.Value;

public class Equal extends Command{
	


	public Equal(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		int result;
		
		 Value term1 = (Value) getParams().get(0);
		 Value term2 = (Value) getParams().get(1);
		
		if(term1.getValue().equals(term2.getValue()))
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
