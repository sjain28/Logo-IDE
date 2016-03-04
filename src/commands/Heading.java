package commands;

public class Heading extends TurtleCommand{

	public Heading(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		super.setValue(getTurtle().getOrientation());
		return getTurtle().getOrientation();
	}

}
