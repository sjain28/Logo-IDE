package frontend;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Window {
	
	private static String TITLE = null;
	private Group root = new Group();
	private static double SLIDER_WIDTH;
	private static double SLIDER_HEIGHT;
	private static double BUTTON_WIDTH = 120;
	private static double BUTTON_HEIGHT = 30;
	private static double BORDER_SIZE;
	private static double FONT_SIZE;
	
	private ArrayList<Text> texts;
	Properties UILabels;
	private ArrayList<Slider> sliders;
	
	public Window(double width, double height) {
		
	}
	
	
	public String getTitle () {
        return TITLE;
    }
	
	public void setTitle(String s) {
		TITLE = s;
	}
	
	public Group getRoot() {
		return root;
	}
	
	
	public abstract Scene init();
	
	public Button initButton(String label, double x, double y, double width, double height) {
		Button newButton = new Button(label);
		
		newButton.setMinWidth(width);
		newButton.setMinHeight(height);
		newButton.setLayoutX(x);
		newButton.setLayoutY(y);
		
		return newButton;
	}
	
	public abstract void step(double elapsedTime);
	
	public Properties openPropertiesFile (String fileName) throws Exception {
		Properties newProperties = new Properties();
		InputStream inputStream = null;
		
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			newProperties.load(inputStream);
			inputStream.close();
			return newProperties;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ComboBox<String> makeComboBox(double x, double y) {
		ComboBox<String> newComboBox = new ComboBox<String>();
		
		newComboBox.setLayoutX(x);
		newComboBox.setLayoutY(y);
		newComboBox.setMinWidth(SLIDER_WIDTH);
		newComboBox.setMinHeight(SLIDER_HEIGHT);
		
		root.getChildren().add(newComboBox);
		
		return newComboBox;
	}
	
	private void makeSliderAndLabel(double x, double y, double min, double max, double start, boolean isInteger, String label)
	{
		Slider newSlider = new Slider(min, max, start);		
		Text newText = new Text();
		
		newSlider.setLayoutX(x);
		newSlider.setLayoutY(y);
		newSlider.setMinWidth(SLIDER_WIDTH);
		newSlider.setMinHeight(SLIDER_HEIGHT);
		
		newText.setFill(Color.BLACK);
        newText.setFont(Font.font("Helvetica" , FONT_SIZE));
        
        newText.setX(x);
        newText.setY(y + BORDER_SIZE/2 + SLIDER_HEIGHT);

		changeTextField(start, isInteger, label, newText);
		
		newSlider.setOnMouseDragged(e -> changeTextField(newSlider.getValue(), isInteger, label, newText));

		texts.add(newText);
		sliders.add(newSlider);
		
        root.getChildren().add(newText);
		root.getChildren().add(newSlider);
	}
	
	private void changeTextField(double newValue, boolean isInteger, String label, Text text)
	{
		String newValueString = UILabels.getProperty(label);
		if (isInteger)
			newValueString = newValueString.concat(String.format("%.0f",(float)Math.round(newValue) ));
		else
			newValueString = newValueString.concat(String.format("%.2f",(float)newValue ));
		
    	text.setText(newValueString);
	}
	
	
	
	

}