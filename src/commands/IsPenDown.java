package commands;

public class IsPenDown extends TurtleCommand{

	public IsPenDown(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		if(getTurtle().isDown()){
			super.setValue(1);
			return 1;
		}	
		super.setValue(0);
		return 0;
	}	
}
