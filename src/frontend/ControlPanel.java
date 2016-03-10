package frontend;

import java.io.File;
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
	private VBox myPaletteBox;
	private VBox myShapeBox;
	private double COLOR_BOX_WIDTH = 90;
	private double COLOR_BOX_HEIGHT = 30;
	private double COMBO_BOX_WIDTH = 80;
	private double palette_BOX_HEIGHT = 60;
	private double palette_BOX_WIDTH = 150;

	
	
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
		backgroundColorPicker = initColorPicker(10, backgroundLabel, Color.WHITE, grid);
		backgroundColorPicker.setOnAction(e -> changeBackgroundColor());

		Label lineColorLabel = new Label("Select Line Color");
		lineColorPicker = initColorPicker(20, lineColorLabel, Color.BLACK, grid);
		lineColorPicker.setOnAction(e -> changeLineColor());

		final FileChooser fileChooser = new FileChooser();
		final Button openButton = initButton("Open a Picture...", grid);
		openButton.setOnAction(e -> handleOpen(fileChooser));
				
		myComboBox = initComboBox();
		Label resourceLabel = new Label("Select Language");
		GridPane.setConstraints(myComboBox,55, 2);
		GridPane.setConstraints(resourceLabel, 55,  0);
		grid.getChildren().add(myComboBox);
		grid.getChildren().add(resourceLabel);
		// Defining the Submit button

		myPaletteBox = initPaletteBox();
		Label paletteLabel = new Label("Palette Options");
		GridPane.setConstraints(myPaletteBox, 70, 2);
		grid.getChildren().add(myPaletteBox);
		GridPane.setConstraints(paletteLabel,  70,  0);
		grid.getChildren().add(paletteLabel);
		
		myShapeBox = initShapeBox();
		Label shapeLabel = new Label("Shape Options");
		GridPane.setConstraints(myShapeBox, 90, 2);
		grid.getChildren().add(myShapeBox);
		GridPane.setConstraints(shapeLabel,  90,  0);
		grid.getChildren().add(shapeLabel);
		
		
		super.getRoot().getChildren().add(grid);
		return myScene;
	}


	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		//updateColorpalette();
		myPaletteBox.getChildren().removeAll();
		myPaletteBox.getChildren().addAll(super.getController().getPalette());
	}
	
	private void changeBackgroundColor() {
		myDisplay.setBackgroundColor(backgroundColorPicker.getValue());
		getController().changePalette(backgroundColorPicker.getValue());
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
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private ColorPicker initColorPicker(int row, Label inLabel, Color myColor, GridPane grid){
		ColorPicker myColorPicker = new ColorPicker();
		GridPane.setConstraints(myColorPicker,  row,  2);
		GridPane.setConstraints(inLabel, row, 0);
		myColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		myColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		myColorPicker.setValue(myColor);
		grid.getChildren().add(myColorPicker);
		grid.getChildren().add(inLabel);
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

	private VBox initPaletteBox(){
		
		 ListView<String> listView = super.getController().getPalette();
		 //ditch magic variables later
		 ObservableList<String> indices = FXCollections.observableArrayList("0,255,0", "255,0,0", "0,0,255", "255,255,255");
				 
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(indices);
	     listView.setCellFactory( e-> handlePCellCreation());

		 box.setPrefWidth(palette_BOX_WIDTH);
		 box.setPrefHeight(palette_BOX_HEIGHT);
	     return box;
	}

	private VBox initShapeBox(){
		ListView<String> listView = new ListView<>();
				//super.getController().getShapes();
		ObservableList<String> shapes = FXCollections.observableArrayList("0", "3", "4", "5", "6");
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(shapes);
	     listView.setCellFactory( e-> handleShapeCellCreation());

		 box.setPrefWidth(palette_BOX_WIDTH);
		 box.setPrefHeight(palette_BOX_HEIGHT);
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
	
	private Button initButton(String myString, GridPane grid){
		Button myButton = new Button(myString);
		GridPane.setConstraints(myButton,  40,  2);
		grid.getChildren().add(myButton);
		return myButton;
	}

}
 


 

