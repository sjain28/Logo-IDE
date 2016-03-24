// This entire file is part of my masterpiece.
// Bobby Wang

package frontend;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import control.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Window {
	
	private static final double ERROR_WIDTH = 400;
	private static final double ERROR_HEIGHT = 400;
	
	private String TITLE;
	private Group root;
	
	private double WINDOW_WIDTH;
	private double WINDOW_HEIGHT;
	Properties UILabels;
	private Controller controller;
	
	public Window(double width, double height) {
		root = new Group();
		WINDOW_WIDTH = width;
		WINDOW_HEIGHT = height;
		TITLE = null;
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
	
	public double getWidth() {
		return WINDOW_WIDTH;
	}
	
	
	public double getHeight() {
		return WINDOW_HEIGHT;
	}
	
	public abstract Scene init();
	
	public abstract void step(double elapsedTime);
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public Properties openPropertiesFile (String fileName) {
		Properties newProperties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			newProperties.load(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newProperties;
	}
	
	
	
	

}