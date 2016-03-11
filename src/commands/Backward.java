package commands;

<<<<<<< HEAD
import value.Value;
=======
import parser.DoubleOptional;
import turtle.Agent;
>>>>>>> backend

public class Backward extends TurtleCommand{

	public Backward(){
		setNumParams(1);
	}
	
	@Override
<<<<<<< HEAD
	public double evaluate() {
		Value distance = (Value) getParams().get(0);
		super.getTurtle().move(-distance.getValue());
=======
	public double doCommand(Agent a) {
		DoubleOptional distance = (DoubleOptional) getParams().get(0);
		a.move(-distance.getValue());
>>>>>>> backend
		super.setValue(distance.getValue());
		return distance.getValue();
	}

}
