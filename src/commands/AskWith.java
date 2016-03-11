package commands;

import java.util.ArrayList;
import java.util.List;

import turtle.Agent;

public class AskWith extends Command{

	private List<Command> predicate;
	private List<Command> contents;
	
	public AskWith(){
		setNumParams(2);
	}
	
	@Override
	public double evaluate() {
		double value = -1;
		ArrayList<Agent> active = new ArrayList(getEnvironment().getActiveTurtles());
		getEnvironment().getActiveTurtles().clear();
		
		for(Agent t: getEnvironment().getTurtles()){
			getEnvironment().getActiveTurtles().add(t);
			value = 0;
			
			for(Command c: predicate){
				value = c.evaluate();
			}
			
			if(value== 0){
				getEnvironment().getActiveTurtles().remove(t);
			}
		}
		
		for(Command c: contents){
			value = c.evaluate();
		}
		getEnvironment().setActiveTurtles(active);
		setValue(value);
		return value;
	}
	
	@Override
	protected void initParams(List<Object> params){
		predicate = (List<Command>) params.get(0);
		contents = (List<Command>) params.get(1);
	}

}
