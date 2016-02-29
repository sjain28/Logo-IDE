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
		super.getTurtle().setOrientation(newHeading.getValue());
		return newHeading.getValue();
	}

}
