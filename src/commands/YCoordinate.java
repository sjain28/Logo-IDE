package commands;

public class YCoordinate extends TurtleCommand{

	public YCoordinate(){
		setNumParams(0);
	}

	@Override
	public double evaluate() {
		super.setValue(getTurtle().getLocation().getY());
		return getTurtle().getLocation().getY();
	}

}
