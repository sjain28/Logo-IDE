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
	
	private Map<String, String> variableStates; // Front end ?
	private Parser parser;

	public Controller() {
		
		variableStates = new HashMap<String, String>();
			}

	public void setVariable(String varName, Double value) {
		myVariables.put(varName, value);
	}
	public Double getVariable(String varName) {
		return myVariables.get(varName);
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
		parser.setAgent(myActiveTurtle);
		
		try {
			List<Command> cmd = parser.parse();
			System.out.println(cmd.size());
			for (Command c : cmd) {
				if(c instanceof TurtleCommand){
					System.out.println("TURTLE!");
					((TurtleCommand) c).setTurtle(myActiveTurtle);
				}
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
