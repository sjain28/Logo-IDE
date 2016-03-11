package commands;

<<<<<<< HEAD
import value.Value;
=======
import parser.DoubleOptional;
import turtle.Agent;
>>>>>>> backend

public class Right extends TurtleCommand{
	
	public Right(){
		setNumParams(1);
	}
	
	@Override
<<<<<<< HEAD
	public double evaluate() {
		Value rotation = (Value) getParams().get(0);
		super.getTurtle().turn(rotation.getValue()); //default is clockwise
=======
	public double doCommand(Agent a) {
		DoubleOptional rotation = (DoubleOptional) getParams().get(0);
		a.turn(rotation.getValue()); //default is clockwise
>>>>>>> backend
		super.setValue(rotation.getValue());
		return rotation.getValue();
	}

}
