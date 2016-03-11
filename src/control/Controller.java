package control;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.Command;
import commands.TurtleCommand;
import frontend.ErrorHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import parser.Parser;

import java.util.ArrayList;
import turtle.*;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
	
	private Map<String, String> variableStates; // Front end ?
	private Parser parser;

	public Controller() {
		variableStates = new HashMap<String, String>();
		parser = new Parser(myResources);
	}
	
	
	
	public String getProperty(String propertyKey) {
		return myResources.getString(propertyKey);
	}
	
	public Map<String, String> getVariableStates() {
		return variableStates;
	}
	
	public void makeParser(String commandString) {
		
		try {
			List<Command> cmd = parser.parse(commandString);
			for (Command c : cmd) {
				c.evaluate();
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
	
	public Agent getTurtle() {  // FOR TESTING, SHOULD BE REMOVED AND CHANGED LATER TO MULTIPLE TURTLES
		return new Turtle(0, new Point2D(0, 0), true, true, Color.BLUE, 3, 0);
	}
	
}
