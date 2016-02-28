package commands;

import java.util.List;

public class Forward extends TurtleCommand{
	
	private Double distance;
	
	public Forward(){
		super.setNumParams(1);
	}
	
	@Override
	public void setParams(List<Object> params) throws Exception{
		try{
			distance =  (Double) params.get(0);
		}
		catch(Exception e){
			throw new Exception();
		}
	}
	
	@Override
	public double evaluate() {
		super.getTurtle().move(distance);
		return distance;
	}

}
