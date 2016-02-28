package commands;

import turtle.Turtle;

public abstract class TurtleCommand extends Command{
	
	private Turtle myTurtle;
	
	protected Turtle getTurtle(){
		return myTurtle;
	}
	
	protected void setTurtle(Turtle t) throws Exception{
		if(myTurtle == null){
			myTurtle = t;
		}
		else{
			throw new Exception();
		}
	}
}

