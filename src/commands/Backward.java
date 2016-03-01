package commands;

import java.util.List;

import parser.DoubleOptional;

public class Backward extends TurtleCommand{

	private DoubleOptional distance;
	
	public int getNumParams(){
		return 1;
	}
	
	@Override
	public void initParams(List<Object> params) throws Exception{
		try{
			distance =  (DoubleOptional) params.get(0);
		}
		catch(Exception e){
			throw new Exception();
		}
	}
	
	@Override
	public double evaluate() {
		super.getTurtle().move(-distance.getValue());
		super.setValue(distance.getValue());
		return distance.getValue();
	}
	
	public DoubleOptional getValue(){
		return distance;
	}
}