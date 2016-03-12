package frontend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import turtle.Agent;

public class ControlPanel extends Window {

	private static final double COLOR_BOX_WIDTH = 90;
	private static final double COLOR_BOX_HEIGHT = 30;
	private static final double COMBO_BOX_WIDTH = 80;
	private static final double PALETTE_BOX_HEIGHT = 60;
	private static final double PALETTE_BOX_WIDTH = 150;
	private static final int PANE_WIDTH = 600;
	private static final int PANE_HEIGHT = 150;
	private static final int BOX_ROW = 2;
	private static final int LABEL_ROW = 0;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";	
	private static final String FRONTEND_RESOURCE_PACKAGE = "resources.frontend/frontend";
	private static final String COLOR_RESOURCE_PACKAGE = "resources.frontend/colors";
		
	private ResourceBundle myResources = ResourceBundle.getBundle(FRONTEND_RESOURCE_PACKAGE);
	
	private ColorPicker backgroundColorPicker;
	private ColorPicker lineColorPicker;
	private HBox myBox;
	private Display myDisplay;
	private ComboBox<String> myComboBox;
	private ResourceBundle myResource;
	private VBox myPaletteBox;
	private VBox myPictureBox;
	private static final int FPS = 60;

	
	public ControlPanel(Display inDisplay) {
		super(PANE_WIDTH, PANE_HEIGHT);
		myDisplay = inDisplay;
	}

	@Override
	public Scene init() {

		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		int currentColumn = 5;
		
		Label backgroundLabel = new Label(myResources.getString("BACKGROUND_LABEL"));
		backgroundColorPicker = initColorPicker(currentColumn++, backgroundLabel, Color.WHITE, grid);
		backgroundColorPicker.setOnAction(e -> changeBackgroundColor());

		Label lineColorLabel = new Label(myResources.getString("LINECOLOR_LABEL"));
		lineColorPicker = initColorPicker(currentColumn++, lineColorLabel, Color.BLACK, grid);
		lineColorPicker.setOnAction(e -> changeLineColor());

		//bunch of magic
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = initButton(myResources.getString("OPEN_PICTURE"), grid, currentColumn++, BOX_ROW);
		openButton.setOnAction(e -> handleOpen(fileChooser));
				
		myComboBox = initComboBox();
		Label languageLabel = new Label(myResources.getString("LANGUAGE_LABEL"));
		addToGrid(grid, myComboBox, languageLabel, currentColumn++, BOX_ROW, LABEL_ROW);
		
		// Defining the Submit button

		myPaletteBox = initPaletteBox();
		Label paletteLabel = new Label(myResources.getString("PALETTE_LABEL"));
		addToGrid(grid, myPaletteBox, paletteLabel, currentColumn++, BOX_ROW, LABEL_ROW);
		
		myPictureBox = initPictureBox();
		Label shapeLabel = new Label(myResources.getString("PICTURE_LABEL"));
		addToGrid(grid, myPictureBox, shapeLabel, currentColumn++, BOX_ROW, LABEL_ROW);

		Button myNewBox = initButton(myResources.getString("WINDOW_PROMPT"), grid, currentColumn++, BOX_ROW);
		myNewBox.setOnAction(e-> setNewWindow());
		
		super.getRoot().getChildren().add(grid);
		return myScene;
	}


	@Override
	public void step(double elapsedTime) {

		myPaletteBox.getChildren().removeAll();
		myPaletteBox.getChildren().addAll(super.getController().getPalette());
		lineColorPicker.setValue(Color.valueOf(getController().getActiveTurtles().get(0).getPenColor().toString()));
		myPictureBox.getChildren().removeAll();
		myPictureBox.getChildren().addAll(super.getController().getPictures());
	}
	
	
	private void changeBackgroundColor() {
		getController().setBackGroundColor(backgroundColorPicker.getValue());
		return;
	}

	private void changeLineColor() {
		Collection<Agent> myActives = getController().getActiveTurtles();
		for(Agent myTurtle: myActives ){
			myTurtle.setPenColor(lineColorPicker.getValue());
		}

		return;
	}
	
	private void changeLineWidth() {
		Collection<Agent> myActives = getController().getActiveTurtles();
		for(Agent myTurtle: myActives ){
			//myTurtle.setPenColor(lineWidthPicker.getValue());
		}

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
				getController().setPictures(fileLocation);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ColorPicker initColorPicker(int column, Label inLabel, Color myColor, GridPane grid){
		ColorPicker myColorPicker = new ColorPicker();
		myColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		myColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		myColorPicker.setValue(myColor);
		addToGrid(grid, myColorPicker, inLabel, column, BOX_ROW, LABEL_ROW);
		return myColorPicker;
	}
	
	private ComboBox<String> initComboBox() {
		ComboBox<String> thisComboBox = new ComboBox<String>();

		
		URL resource = ClassLoader.getSystemClassLoader().getResource("resources/languages");
		Collection<String> myFileList = new ArrayList<String>();
		File head = new File(resource.getPath());
		for(File fileEntry: head.listFiles()){
			myFileList.add(fileEntry.getName().substring(0, fileEntry.getName().indexOf('.')));
		}
		
		thisComboBox.getItems().addAll(myFileList);
		thisComboBox.setValue(myResources.getString("DEFAULT_LANGUAGE"));
		thisComboBox.setOnAction(e -> handleCombo());
		thisComboBox.setPromptText(myResources.getString("LANGUAGE_PROMPT"));
		thisComboBox.setPrefWidth(COMBO_BOX_WIDTH);
		thisComboBox.setPrefHeight(COLOR_BOX_HEIGHT);
		
		return thisComboBox;

	}

	private void handleCombo() {
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myComboBox.getValue());
		getController().changeLanguage(myResource);
	}

	private VBox initPaletteBox(){
		
		 ListView<String> listView = super.getController().getPalette();
		 ResourceBundle myColors = ResourceBundle.getBundle(COLOR_RESOURCE_PACKAGE);
		 
		 Collection<String> myInColors = new ArrayList<>();
		 for( String key: myColors.keySet()){
			 myInColors.add(myColors.getString(key));
		 }
		 ObservableList<String> indices = FXCollections.observableArrayList(myInColors);
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);
	     listView.setItems(indices);
	     listView.setCellFactory( e-> handleColorCellCreation());
		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}

	private VBox initPictureBox(){
		 ListView<String> listView =super.getController().getPictures();
				//super.getController().getShapes();
		 //magic
		URL resource = ClassLoader.getSystemClassLoader().getResource("resources/images");
		Collection<String> myFileList = new ArrayList<String>();
		File head = new File(resource.getPath());
		for(File fileEntry: head.listFiles()){
			myFileList.add(fileEntry.toURI().toString());
		}
		 
		 ObservableList<String> shapes = FXCollections.observableArrayList(myFileList);
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(shapes);
	     listView.setCellFactory( e-> handlePictureCellCreation());

		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}
	
	private ListCell<String> handleColorCellCreation(){
		
		ListCell<String> myVal = new ColorRectCell();
		return myVal;

	}
	
	private ListCell<String> handlePictureCellCreation(){
		
		ListCell<String> myVal = new ImageCell();
		return myVal;

	}
	
	private Button initButton(String myString, GridPane grid, int column, int row){
		Button myButton = new Button(myString);
		GridPane.setConstraints(myButton,  column,  row);
		grid.getChildren().add(myButton);
		return myButton;
	}

	private void addToGrid(GridPane grid, Node toAdd, Node label, int column, int toAddRow, int labelRow){
		GridPane.setConstraints(toAdd, column, toAddRow);
		grid.getChildren().add(toAdd);
		GridPane.setConstraints(label,  column,  labelRow);
		grid.getChildren().add(label);
	}
	
	private void setNewWindow(){
		Stage stage = new Stage();
 		Controller myBackEnd =  new Controller();
		GUI myFrontEnd = new GUI(FPS, myBackEnd);
        Scene scene = myFrontEnd.init();
        stage.setScene(scene);
        stage.show();
	}
	
}
 


 

