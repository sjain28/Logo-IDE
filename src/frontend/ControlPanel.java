package frontend;

import java.io.File;
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

public class ControlPanel extends Window {

	private ColorPicker backgroundColorPicker;
	private ColorPicker lineColorPicker;
	private HBox myBox;
	private Display myDisplay;
	private ComboBox<String> myComboBox;
	private final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResource;
	private VBox myPaletteBox;
	private VBox myShapeBox;
	private double COLOR_BOX_WIDTH = 90;
	private double COLOR_BOX_HEIGHT = 30;
	private double COMBO_BOX_WIDTH = 80;
	private double PALETTE_BOX_HEIGHT = 60;
	private double PALETTE_BOX_WIDTH = 150;

	private final int BOX_ROW = 2;
	private final int LABEL_ROW = 0;
	
	public ControlPanel(Scene inScene, Display inDisplay) {
		super(600, 150);
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
		// Defining the Name text field
		
		Label backgroundLabel = new Label("Select Background Color");
		backgroundColorPicker = initColorPicker(5, backgroundLabel, Color.WHITE, grid);
		backgroundColorPicker.setOnAction(e -> changeBackgroundColor());

		Label lineColorLabel = new Label("Select Line Color");
		lineColorPicker = initColorPicker(15, lineColorLabel, Color.BLACK, grid);
		lineColorPicker.setOnAction(e -> changeLineColor());

		//bunch of magic
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = initButton("Open a Picture...", grid, 25, 2);
		openButton.setOnAction(e -> handleOpen(fileChooser));
				
		myComboBox = initComboBox();
		Label languageLabel = new Label("Select Language");
		addToGrid(grid, myComboBox, languageLabel, 40, 2, 0);
		// Defining the Submit button

		myPaletteBox = initPaletteBox();
		Label paletteLabel = new Label("Palette Options");
		addToGrid(grid, myPaletteBox, paletteLabel, 50, 2, 0);
		
		myShapeBox = initShapeBox();
		Label shapeLabel = new Label("Shape Options");
		addToGrid(grid, myShapeBox, shapeLabel, 60, 2, 0);

		Button myNewBox = initButton("Make a new window", grid, 70, 2);
		myNewBox.setOnAction(e-> setNewWindow());
		
		super.getRoot().getChildren().add(grid);
		return myScene;
	}


	@Override
	public void step(double elapsedTime) {

		myPaletteBox.getChildren().removeAll();
		myPaletteBox.getChildren().addAll(super.getController().getPalette());
		lineColorPicker.setValue(Color.valueOf(getController().getTurtle().getPenColor().toString()));
	}
	
	private void changeBackgroundColor() {

		//myDisplay.setBackgroundColor(backgroundColorPicker.getValue());
		getController().setBackGroundColor(backgroundColorPicker.getValue());

		return;
	}

	private void changeLineColor() {
//TODO:	getController().getActiveTurtle().setPenColor(lineColorPicker.getValue());
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
		// magic
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

	private VBox initPaletteBox(){
		
		 ListView<String> listView = super.getController().getPalette();
		 //ditch magic variables later
		 ObservableList<String> indices = FXCollections.observableArrayList("0,255,0", "255,0,0", "0,0,255", "255,255,255");
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);
	     listView.setItems(indices);
	     listView.setCellFactory( e-> handlePCellCreation());
		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}

	private VBox initShapeBox(){
		 ListView<String> listView = new ListView<>();
				//super.getController().getShapes();
		 //magic
		 ObservableList<String> shapes = FXCollections.observableArrayList("0", "3", "4", "5", "6");
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(shapes);
	     listView.setCellFactory( e-> handleShapeCellCreation());

		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}
	
	private ListCell<String> handlePCellCreation(){
		
		ListCell<String> myVal = new ColorRectCell();
		return myVal;

	}
	
	private ListCell<String> handleShapeCellCreation(){
		
		ListCell<String> myVal = new ShapeCell();
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
        stage.setTitle("My New Stage Title");        
        
 		Controller myBackEnd =  new Controller();
		GUI myFrontEnd = new GUI(60, myBackEnd);
        Scene scene = myFrontEnd.init();
        stage.setScene(scene);
        stage.show();
	}
	
}
 


 

