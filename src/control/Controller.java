package control;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import commands.Command;
import commands.TurtleCommand;
import frontend.ErrorHandler;
import parser.Parser;

import java.util.ArrayList;

import turtle.*;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
	
	private Agent myActiveTurtle;
	private Collection<Agent> myTurtles;
	private Map<String, String> variableStates;
	
	private Parser parser;
	
	public Controller() {
		
		variableStates = new HashMap<String, String>();
		myTurtles = new ArrayList<Agent>();
		

		//Test code
		variableStates.put("Hello", "500");
		
		
		// TODO Auto-generated constructor stub
	}

	public void setActiveTurtle(Agent turtle) {
		myActiveTurtle = turtle;
	}
	
	public Agent getActiveTurtle() {
		return myActiveTurtle;
	}
	
	public String getProperty(String propertyKey) {
		return myResources.getString(propertyKey);
	}
	
	public Map<String, String> getVariableStates() {
		return variableStates;
	}
	
	public void makeParser(String commandString) {
		parser = new Parser(commandString);
		try {
			parser.parse();
			for (Command c : parser.myCommands) {
				if(c instanceof TurtleCommand){
					TurtleCommand d = (TurtleCommand) c;
					d.setTurtle(myActiveTurtle);
					d.evaluate();
				}else{
					c.evaluate();
				}
			}
			for(State s: ((Turtle) myActiveTurtle).getStates()){
				System.out.println(s.getLocation() + "  " + s.getOrientation());
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			ErrorHandler eh = new ErrorHandler(50, 50);
			eh.init();
			eh.openError("InvalidInputException");
		}
	}
	
}
