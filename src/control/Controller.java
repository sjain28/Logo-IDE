package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.Command;
import frontend.ErrorHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import parser.Parser;
import turtle.Agent;
import turtle.State;
import turtle.Turtle;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
	
	private Agent myActiveTurtle;
	private List<Agent> myTurtles;
	private Map<String, Double> myVariables;
	private Map<String, String> variableStates;
	private Parser parser;

	public Controller() {
		
		variableStates = new HashMap<String, String>();
		myTurtles = new ArrayList<Agent>();
		
		//Test code
		
		// TODO Auto-generated constructor stub
		myVariables = new HashMap<String, Double>();
	}

	public void setVariable(String varName, Double value) {
		myVariables.put(varName, value);
	}
	public Double getVariable(String varName) {
		return myVariables.get(varName);
	}
	
	public void setActiveTurtle(Agent turtle) {
		myActiveTurtle = turtle;
		myTurtles.add(turtle);
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
		parser = new Parser(commandString, myResources);
		myActiveTurtle.init();
		parser.addTurtle(myActiveTurtle);
		Turtle second = new Turtle(0, new Point2D(10,10), true, true, Color.BLUE, 3, 0);
		parser.addTurtle(second);
		//double orient, Point2D loc, boolean down, boolean visible, Paint pen, double line, int curTime
		
		try {
			List<Command> cmd = parser.parse();
			for (Command c : cmd) {
				c.evaluate();
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
	
	public void changeLanguage(ResourceBundle newLanguage) {
		myResources = newLanguage;
	}
	
}
