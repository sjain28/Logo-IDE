package control;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import turtle.Agent;
import turtle.State;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);
	
	private Agent myActiveTurtle;
	private Collection<Agent> myTurtles;
	
	
	public Controller() {
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
	
}
