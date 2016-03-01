package frontend;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControlPanel {

	private Scene myScene;
	private ColorPicker backgroundColorPicker;
	private ColorPicker lineColorPicker;
	private HBox myBox;
	private ImageView myImageView;
	private Display myDisplay;
	private ComboBox myComboBox;
	private final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResource;
	private ColorPicker imageBackgroundPicker;
	
	public ControlPanel(Scene inScene, Display inDisplay){
		myDisplay = inDisplay;
		myScene = inScene;
		myBox = new HBox();
		myBox.setPadding(new Insets(15, 12, 15, 12));
		myBox.setSpacing(10);

		backgroundColorPicker = new ColorPicker();
	    backgroundColorPicker.setValue(Color.WHITE);
	    backgroundColorPicker.setOnAction( e-> changeBackgroundColor() ); 
	    
	    //backgroundColorPicker.setLayoutX(myScene.getWidth()*3/4);
	    
	    lineColorPicker = new ColorPicker();
	    lineColorPicker.setOnAction(e -> changeLineColor() ); 
	    
	    imageBackgroundPicker = new ColorPicker();
	    imageBackgroundPicker.setOnAction(e -> changeImageBackgroundColor() ); 
	    
	    
	    final FileChooser fileChooser = new FileChooser();
	    final Button openButton = new Button("Open a Picture...");
	    
	    openButton.setOnAction(
	            e-> handleOpen(fileChooser ));
	    
	    
	    myComboBox = new ComboBox();
        myComboBox.getItems().addAll(
        		"Chinese",
        		"English",
        		"French",
        		"German",
        		"Italian",
        		"Portugese",
        		"Russian",
        		"Spanish",
        		"Syntax"
          
        );
	    
        Label myLabel = new Label("Display Background");
        Label myLabel2 = new Label ("Line Color");
        Label myLabel3 = new Label ("Select Language");
        Label myLabel4 = new Label ("Select Image Background Color");
        
        
        myComboBox.setOnAction(e -> handleCombo());
		myBox.getChildren().addAll(myLabel, backgroundColorPicker, myLabel2, lineColorPicker, 
				openButton, myLabel3, myComboBox, myLabel4, imageBackgroundPicker);
	}

	private Object changeImageBackgroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	void changeBackgroundColor(){
		myDisplay.getRectangle().setFill(backgroundColorPicker.getValue());
		return;
	}
	
	void changeLineColor(){
		lineColorPicker.getValue();
		return;
	}
	
	public HBox getControlPanel(){
		return myBox;
	}
	
	void handleOpen(FileChooser fileChooser){
		//this.get
		Stage stage = new Stage();
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
            	openFile(file);
            } catch(Exception e){
            	System.out.println(e);
            }
        }
	}
	
	void openFile(File file) throws IOException{
	 try {
         BufferedImage bufferedImage = ImageIO.read(file);
         Image image = SwingFXUtils.toFXImage(bufferedImage, null);
         myImageView.setImage(image);
     } catch (IOException ex) {
         throw(ex);
     }
	}
	
	void handleCombo(){
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myComboBox.getValue());
	}
}
