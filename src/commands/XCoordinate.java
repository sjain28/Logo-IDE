package commands;

public class XCoordinate extends TurtleCommand{

	public XCoordinate(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		super.setValue(getTurtle().getLocation().getX());
		return getTurtle().getLocation().getX();
	}

}
