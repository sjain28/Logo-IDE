package commands;

import java.util.List;

import parser.DoubleOptional;

public class SetHeading extends TurtleCommand{
	
	private DoubleOptional newHeading;
	
	@Override
	public int getNumParams() {
		return 1;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			newHeading = (DoubleOptional) params.get(0);
		}
		catch(Exception e){
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		double firstHeading = getTurtle().getOrientation();
		super.getTurtle().setOrientation(newHeading.getValue());
		double result = firstHeading - getTurtle().getOrientation();
		super.setValue(result);
		return result;
	}

}
