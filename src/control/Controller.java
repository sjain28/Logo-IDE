package control;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import commands.Command;
import frontend.DialogHandler;
import javafx.scene.paint.Color;
import parser.Environment;
import parser.Parser;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import turtle.Agent;


public class Controller {
	public static final String DEFAULT_LANGUAGE = "resources.languages/English";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE);

	private Parser parser;
	private Environment curEnv;
	
	private ListView<String> myPalette = new ListView<>();
	private Color backgroundColor = Color.WHITE;
	
	private ListView<String> myPictures = new ListView<>();
	private String imageLocation = "resources/images/Spiny.png";
	
	public Controller() {
		parser = new Parser(myResources);
		curEnv = parser.getGlobalEnvironment(); // Only global environment at the moment
	}
	
	public String getProperty(String propertyKey) {
		return myResources.getString(propertyKey);
	}
	
	public ResourceBundle getResources() {
		return myResources;
	}
	
	
	public void makeParser(String commandString) {		
		try {
			List<Command> cmd = parser.parse(commandString);
			for (Command c : cmd) {
				c.evaluate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			DialogHandler eh = new DialogHandler(50, 50);
			eh.init();
			eh.openPopup("InvalidInputException");
		}
	}
	
	public void changeLanguage(ResourceBundle newLanguage) {
		myResources = newLanguage;
	}
	
	public void setPictures(String filePath){
		myPictures.getItems().add(filePath);
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

	
	public Agent getTurtle() { 
		return parser.getTurtle();
	}

	public List<Agent> getAllTurtles() { // The proper method that should be called, allows for multiple turtles
		return curEnv.getTurtles();
	}
	
	public List<Agent> getActiveTurtles() {
		return curEnv.getActiveTurtles();
	}
	
	public Map<String, Double> getVariables() { 
		return curEnv.getVariables(); 
	}
	
	public ListView<String> getPalette(){
		return myPalette;
	}
	
	public ListView<String> getPictures(){
		return myPictures;
	}
		
	public Color getColor(int index){
		if(myPalette.getItems().size() > index){
			return Color.web("rgb(" + myPalette.getItems().get(index) + ")");
		} else{
			return Color.BLACK;
		}
	}
	
	public Image getImage(int index){
		if(myPictures.getItems().size() > index){
			return new Image(myPictures.getItems().get(index));
		}else{
			return null;
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
	
	
	public void setOverallPalette(List<String> textPaletteList) {
		myPalette = new ListView<String>(FXCollections.observableArrayList(textPaletteList));
	}
	
	public void setVariables(Map<String, Double> variables) {
		curEnv.setAllVariables(variables);
	}
	
	public Environment getEnvironment() {
		return curEnv;
	}
	
	public String getImageLocation(){
		return imageLocation;
	}
	
	public void setImageLocation(String inString){
		imageLocation = inString;
	}
	
	
}
