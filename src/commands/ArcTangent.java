package commands;


import value.Value;

public class ArcTangent extends Command{
	
	public ArcTangent(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		Value term1 = (Value) getParams().get(0);
		double result = Math.atan(term1.getValue());
		super.setValue(result);
		return result;
	}

}
