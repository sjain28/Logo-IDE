package commands;

import java.util.List;

import parser.DoubleOptional;

public class Left extends TurtleCommand{
	
	private DoubleOptional rotation;
	
	public int getNumParams(){
		return 1;
	}

	@Override
	public double evaluate() {
		super.getTurtle().turn(-rotation.getValue()); //default is clockwise
		return rotation.getValue();
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			rotation = (DoubleOptional) params.get(0);
		}
		catch(Exception e){
			throw new Exception();
		}
		
	}

}
