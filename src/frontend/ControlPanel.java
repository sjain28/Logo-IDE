package frontend;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControlPanel extends Window {

	private Scene myScene;
	private ColorPicker backgroundColorPicker;
	private ColorPicker lineColorPicker;
	private HBox myBox;
	private Display myDisplay;
	private ComboBox<String> myComboBox;
	private final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResource;
	private VBox myPalleteBox;
	private double COLOR_BOX_WIDTH = 90;
	private double COLOR_BOX_HEIGHT = 30;
	private double COMBO_BOX_WIDTH = 80;
	private double PALLETE_BOX_HEIGHT = 60;
	private double PALLETE_BOX_WIDTH = 150;

	
	
	public ControlPanel(Scene inScene, Display inDisplay) {
		super(600, 150);
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
		
		Label backgroundLabel = new Label("Select Background Color");
		backgroundColorPicker = initColorPicker(10, backgroundLabel);
		backgroundColorPicker.setValue(Color.WHITE);
		backgroundColorPicker.setOnAction(e -> changeBackgroundColor());
		grid.getChildren().add(backgroundColorPicker);
		grid.getChildren().add(backgroundLabel);
		
		
		Label lineColorLabel = new Label("Select Line Color");
		lineColorPicker = initColorPicker(30, lineColorLabel);
		lineColorPicker.setOnAction(e -> changeLineColor());
		lineColorPicker.setValue(Color.BLACK);
		grid.getChildren().add(lineColorPicker);
		grid.getChildren().add(lineColorLabel);
		
		
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = new Button("Open a Picture...");

		GridPane.setConstraints(openButton,  60,  2);
		openButton.setOnAction(e -> handleOpen(fileChooser));
		grid.getChildren().add(openButton);
				
		myComboBox = initComboBox();
		Label resourceLabel = new Label("Select Language");
		GridPane.setConstraints(myComboBox,80, 2);
		GridPane.setConstraints(resourceLabel, 80,  0);
		grid.getChildren().add(myComboBox);
		grid.getChildren().add(resourceLabel);
		// Defining the Submit button

		myPalleteBox = initPalleteBox();
		
		//Label palleteLabel = new Label("Pallete Options");
		GridPane.setConstraints(myPalleteBox, 120, 2);
		grid.getChildren().add(myPalleteBox);

		super.getRoot().getChildren().add(grid);
		return myScene;
	}


	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub

	}
	
	private void changeBackgroundColor() {
		myDisplay.setBackgroundColor(backgroundColorPicker.getValue());
		return;
	}

	private void changeLineColor() {
		getController().getActiveTurtle().setPenColor(lineColorPicker.getValue());
		return;
	}

	public HBox getControlPanel() {
		return myBox;
	}

	private void handleOpen(FileChooser fileChooser) {
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

	private ColorPicker initColorPicker(int row, Label inLabel){
		ColorPicker myColorPicker = new ColorPicker();
		GridPane.setConstraints(myColorPicker,  row,  2);
		GridPane.setConstraints(inLabel, row, 0);
		myColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		myColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		return myColorPicker;
	}
	
	private ComboBox<String> initComboBox() {
		ComboBox<String> thisComboBox = new ComboBox<String>();

		thisComboBox.getItems().addAll("Chinese", "English", "French", "German", "Italian", "Portugese", "Russian",
				"Spanish", "Syntax");
		thisComboBox.setValue("English");
		thisComboBox.setOnAction(e -> handleCombo());

		thisComboBox.setPromptText("Select Language:");
		thisComboBox.setPrefWidth(COMBO_BOX_WIDTH);
		thisComboBox.setPrefHeight(COLOR_BOX_HEIGHT);
		
		return thisComboBox;

	}

	private void handleCombo() {
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myComboBox.getValue());
		getController().changeLanguage(myResource);
	}

	private VBox initPalleteBox(){
		
		 ListView<String> listView = new ListView<String>();
		 ObservableList<String> indices = FXCollections.observableArrayList("0,255,0", "255,0,0", "0,0,255", "255,255,255");
				 
		 VBox box = new VBox();
	     Scene scene = new Scene(box, 200, 200);
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(indices);
	     listView.setCellFactory( e-> handleCellCreation());
	    		 
	    	/*	 new Callback<ListView<String>, 
	         ListCell<String>>() {
	             @Override 
	             public ListCell<String> call(ListView<String> list) {
	                 return new ColorRectCell();
	             }
			*/
		 box.setPrefWidth(PALLETE_BOX_WIDTH);
		 box.setPrefHeight(PALLETE_BOX_HEIGHT);
	     return box;
	}

	private ListCell<String> handleCellCreation(){
		
		ListCell<String> myVal = new ColorRectCell();
		return myVal;

	}
}
 


 

