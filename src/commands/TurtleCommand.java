package commands;

import java.util.ArrayList;
import java.util.List;

import turtle.Agent;

public abstract class TurtleCommand extends Command{
	
	private Agent myTurtle;
	private List<Agent> myTurtles = new ArrayList<Agent>();
	
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
	
	public void setTurtles(List<Agent> turtles){
		myTurtles = turtles;
	}
	
	@Override
	public double evaluate(){
		double val = -1;
		for(Agent a: myTurtles){
			val = doCommand(a);
		}
		
		return val;
	}
	
	public abstract double doCommand(Agent t);
	
}

