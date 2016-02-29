package commands;

import turtle.Agent;
import turtle.Turtle;

public abstract class TurtleCommand extends Command{
	
	private Agent myTurtle;
	
	protected Agent getTurtle(){
		return myTurtle;
	}
	
	protected void setTurtle(Agent t) throws Exception{
		if(myTurtle == null){
			myTurtle = t;
		}
		else{
			throw new Exception(); 
		}
	}
}

