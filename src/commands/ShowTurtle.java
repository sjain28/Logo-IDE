package commands;

public class ShowTurtle extends TurtleCommand{

	public ShowTurtle(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		getTurtle().changeVisibility(true);
		super.setValue(1);
		return 1;
	}

}
