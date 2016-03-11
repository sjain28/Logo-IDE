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

import javafx.scene.control.ListView;
import turtle.Agent;
import turtle.Turtle;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);

//	private Agent myActiveTurtle;
//	private List<Agent> myTurtles;
//	private Map<String, Double> myVariables;
	private Map<String, String> variableStates;
	private Parser parser;
	
	private ListView<String> myPalette = new ListView<>();
	private Color backgroundColor = Color.WHITE;
	
	public Controller() {
		variableStates = new HashMap<String, String>();
		parser = new Parser(myResources);
//		myTurtles = new ArrayList<Agent>();
//		myVariables = new HashMap<String, Double>();
	}

	
//	public void setActiveTurtle(Agent turtle) {
//		myActiveTurtle = turtle;
//		myTurtles.add(turtle);
//	}
	
	
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
	
	public void setPalette(int index, int r, int g, int b){

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
	public void setPalette(Color myColor){
		int index = 0;
		// the input needs to be added in the form "r,g,b" where r g and b are 
		// doubles
		int r = (int)(myColor.getRed()*255);
		int g = (int)(myColor.getGreen()*255);
		int b = (int)(myColor.getBlue()*255);
	}

	public Agent getTurtle() {  // TODO: FOR TESTING, SHOULD BE REMOVED AND CHANGED LATER TO MULTIPLE TURTLES
		return parser.getTurtle();
	}
	
	public void setPallette(int index, int r, int g, int b){
		String element = String.valueOf(r) + "," + String.valueOf(g) + "," + String.valueOf(b);
		if(myPalette.getItems().size() > index){
			myPalette.getItems().set(index, element);
		} else{
			myPalette.getItems().add(element);
		}
	}
	
	public ListView<String> getPalette(){
		return myPalette;
	}
	
	public Color getColor(int index){
		if(myPalette.getItems().size() > index){
			return Color.web("rgb(" + myPalette.getItems().get(index) + ")");
		} else{
			return Color.BLACK;
		}
	}
	
	public int getColorIndex(Color c){
		int r = (int)(c.getRed()*255);
		int g = (int)(c.getGreen()*255);
		int b = (int)(c.getBlue()*255);
		String myComparer = "rgb(" + String.valueOf(r) + "," + String.valueOf(g) + "," + String.valueOf(b) + ")";
		for( String element: myPalette.getItems() ){
			if(myComparer.equals(element)){
				return myPalette.getItems().indexOf(element);
			}
		}
		return 0;
	}
	
	public void setBackGroundColor(Color c){
		backgroundColor = c;
	}
	public Color getBackgroundColor(){
		return backgroundColor;
	}
}
