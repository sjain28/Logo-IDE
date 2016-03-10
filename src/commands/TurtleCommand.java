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
		setTurtles(getParser().getActiveTurtles()); //Eventually TurtleCommand will get turtles from its environment, not its parser
		double val = -1;
		System.out.println("NUMBER OF TURTLES IN COMMAND: " + myTurtles.size());
		for(Agent a: myTurtles){
			val = doCommand(a);
			System.out.println("After " + a.getLocation());
		}
		
		return val;
	}
	
	public abstract double doCommand(Agent t);
	
}

