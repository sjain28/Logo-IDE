package commands;

public class HideTurtle extends TurtleCommand{

	public HideTurtle(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		getTurtle().changeVisibility(false);
		super.setValue(0);
		return 0;
	}

}
