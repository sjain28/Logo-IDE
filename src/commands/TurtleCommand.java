package commands;

import turtle.Agent;

public abstract class TurtleCommand extends Command{
	
	private Agent myTurtle;
	
	protected Agent getTurtle(){
		return myTurtle;
	}
	
	public void setTurtle(Agent t) throws Exception{
		if(myTurtle == null){
			myTurtle = t;
		}
		else{
			throw new Exception(); 
		}
	}
}

