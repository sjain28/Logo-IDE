package frontend;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControlPanel extends Window {

	private Scene myScene;
	private ColorPicker backgroundColorPicker;
	private ColorPicker lineColorPicker;
	private HBox myBox;
	private Display myDisplay;
	private ComboBox<String> myComboBox;
	private final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResource;
	private ColorPicker imageBackgroundPicker;
	private Desktop desktop = Desktop.getDesktop();
	private double COLOR_BOX_WIDTH = 40;
	private double COLOR_BOX_HEIGHT = 20;

	public ControlPanel(Scene inScene, Display inDisplay) {
		super(600, 100);
		myDisplay = inDisplay;
		myScene = inScene;
	}

	@Override
	public Scene init() {

		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setVgap(5);
		grid.setHgap(5);
		// Defining the Name text field
		
		backgroundColorPicker = new ColorPicker();
		GridPane.setConstraints(backgroundColorPicker,  0,  0);
		backgroundColorPicker.setPromptText("Select Language:");
		backgroundColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		backgroundColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		backgroundColorPicker.setValue(Color.WHITE);
		backgroundColorPicker.setOnAction(e -> changeBackgroundColor());
		grid.getChildren().add(backgroundColorPicker);
		

		// backgroundColorPicker.setLayoutX(myScene.getWidth()*3/4);
		lineColorPicker = new ColorPicker();
		GridPane.setConstraints(lineColorPicker,  5,  0);
		lineColorPicker.setPromptText("Select Line Color:");
		lineColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		lineColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		lineColorPicker.setOnAction(e -> changeLineColor());
		grid.getChildren().add(lineColorPicker);
		
		
		imageBackgroundPicker = new ColorPicker();
		imageBackgroundPicker.setOnAction(e -> changeImageBackgroundColor());
		GridPane.setConstraints(lineColorPicker,  10,  0);
		imageBackgroundPicker.setPromptText("Select Background Image Color");
		imageBackgroundPicker.setPrefWidth(COLOR_BOX_WIDTH);
		imageBackgroundPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		grid.getChildren().add(imageBackgroundPicker);
		
		
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = new Button("Open a Picture...");

		GridPane.setConstraints(openButton,  15,  0);
		openButton.setOnAction(e -> handleOpen(fileChooser));
		grid.getChildren().add(openButton);
		
		final ComboBox myComboBox = initComboBox();
		GridPane.setConstraints(myComboBox, 20, 0);
		grid.getChildren().add(myComboBox);
		// Defining the Submit button
		
	

		super.getRoot().getChildren().add(grid);
		return myScene;
	}


	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub

	}
	
	private Object changeImageBackgroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	void changeBackgroundColor() {
		myDisplay.setBackgroundColor(backgroundColorPicker.getValue());
		return;
	}

	void changeLineColor() {
		lineColorPicker.getValue();
		return;
	}

	public HBox getControlPanel() {
		return myBox;
	}

	void handleOpen(FileChooser fileChooser) {
		// this.get
		Stage stage = new Stage();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			try {
				String fileLocation = file.toURI().toString();
				Image myImage = new Image(fileLocation);
				myDisplay.setImage(myImage);
				// System.out.println(fileLocation);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private ComboBox initComboBox() {
		ComboBox thisComboBox = new ComboBox<String>();

		thisComboBox.getItems().addAll("Chinese", "English", "French", "German", "Italian", "Portugese", "Russian",
				"Spanish", "Syntax");
		thisComboBox.setValue("English");
		thisComboBox.setOnAction(e -> handleCombo());

		thisComboBox.setPromptText("Select Language:");
		thisComboBox.setPrefWidth(40);
		thisComboBox.setPrefHeight(20);
		
		return thisComboBox;

	}

	void handleCombo() {
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myComboBox.getValue());
	}

	

}
