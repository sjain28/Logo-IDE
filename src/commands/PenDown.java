package commands;

public class PenDown extends TurtleCommand{

	public PenDown(){
		setNumParams(0);
	}
	@Override
	public double evaluate() {
		getTurtle().changePenVisibility(true);
		super.setValue(1);
		return 1;
	}

}
