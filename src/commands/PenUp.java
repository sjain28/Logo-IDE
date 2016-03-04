package commands;

public class PenUp extends TurtleCommand{
	
	public PenUp(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		getTurtle().changePenVisibility(false);
		super.setValue(0);
		return 0;
	}

}
