package control;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;

import turtle.Agent;
import turtle.State;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
	
	private Agent myActiveTurtle;
	private Collection<Agent> myTurtles;
	private Map<String, String> variableStates;
	
	
	public Controller() {
		
		//Test code
		variableStates = new HashMap<String, String>();
		myTurtles = new ArrayList<Agent>();
		
		
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
	
}
