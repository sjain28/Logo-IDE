package commands;

import java.util.ArrayList;
import java.util.List;

import turtle.Agent;

public abstract class TurtleCommand extends Command{
	
	private List<Agent> myTurtles = new ArrayList<Agent>();

	public void setTurtles(List<Agent> turtles){
		myTurtles = turtles;
	}
	
	@Override
	public double evaluate(){
		setTurtles(getEnvironment().getActiveTurtles());
		double val = -1;
		for(Agent a: myTurtles){
			val = doCommand(a);
		}
		return val;
	}
	
	public abstract double doCommand(Agent t);
	
}

