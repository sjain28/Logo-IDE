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
		setTurtles(getEnvironment().getActiveTurtles()); //Eventually TurtleCommand will get turtles from its environment, not its parser
		double val = -1;
		for(Agent a: myTurtles){
			val = doCommand(a);
//			System.out.println("After " + a.getLocation() + " " + a.getOrientation());
		}
		return val;
	}
	
	public abstract double doCommand(Agent t);
	
}

