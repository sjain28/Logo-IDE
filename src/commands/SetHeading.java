package commands;

<<<<<<< HEAD
import value.Value;
=======
import parser.DoubleOptional;
import turtle.Agent;
>>>>>>> backend

public class SetHeading extends TurtleCommand{
	
	public SetHeading(){
		setNumParams(1);
	}
	
	@Override
<<<<<<< HEAD
	public double evaluate() {
		double firstHeading = getTurtle().getOrientation();
		Value newHeading = (Value) getParams().get(0);
		super.getTurtle().setOrientation(newHeading.getValue());
		double result = firstHeading - getTurtle().getOrientation();
=======
	public double doCommand(Agent a) {
		double firstHeading = a.getOrientation();
		DoubleOptional newHeading = (DoubleOptional) getParams().get(0);
		a.setOrientation(newHeading.getValue());
		double result = firstHeading - a.getOrientation();
>>>>>>> backend
		super.setValue(result);
		return result;
	}

}
