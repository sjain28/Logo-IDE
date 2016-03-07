package commands;


import parser.DoubleOptional;

public class ArcTangent extends Command{
	
	public ArcTangent(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		DoubleOptional term1 = (DoubleOptional) getParams().get(0);
		double result = Math.atan(term1.getValue());
		super.setValue(result);
		return result;
	}

}
