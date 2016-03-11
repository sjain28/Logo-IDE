package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.Command;
import frontend.ErrorHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
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
	
	private ListView<String> myPalette = new ListView<>();
	private Color backgroundColor = Color.WHITE;
	
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
	
	public void changePalette(int index, int r, int g, int b){

		// the input needs to be added in the form "r,g,b" where r g and b are 
		// ints between 0 and 255
		String element = String.valueOf(r) + "," + String.valueOf(g) + "," + String.valueOf(b);
		if(myPalette.getItems().size() > index){
			myPalette.getItems().set(index, element);
		} else{
			myPalette.getItems().add(element);
		}
	}
	
	// debugging method used to show that it works. Changing
	// a value in the set background will change the zeroth position
	// in the palette
	public void changePalette(Color myColor){
		int index = 0;
		// the input needs to be added in the form "r,g,b" where r g and b are 
		// doubles
		int r = (int)(myColor.getRed()*255);
		int g = (int)(myColor.getGreen()*255);
		int b = (int)(myColor.getBlue()*255);
		
		String element = String.valueOf(r) + "," + String.valueOf(g) + "," + String.valueOf(b);
		if(myPalette.getItems().size() > index){
			myPalette.getItems().set(0, element);
		} else{
			myPalette.getItems().add(element);
		}
	}
	
	public ListView<String> getPalette(){
		return myPalette;
	}
	
	public Color getColor(int index){
		// currently returns white on error - could have try catch block instead
		// possible index out of bounds error
		if(myPalette.getItems().size() < index){
			return Color.web("rgb(" + myPalette.getItems().get(index) + ")");
		} else{
			return Color.WHITE;
		}
	}
	
	public void setBackGroundColor(Color c){
		backgroundColor = c;
	}
	public Color getBackgroundColor(){
		return backgroundColor;
	}
}
