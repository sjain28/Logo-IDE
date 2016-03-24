package frontend;

import java.io.InputStream;
import java.util.Properties;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DialogHandler extends Window {
	
	private Properties errorProperties;
	private Stage stage;
	private Scene scene;
	private Group root;
	private Text errorPrompt;
	private Button closeDialog;
	
	private static final String PROPERTIES_LOCATION = "resources/errors/errors.properties";
	private static final String ERROR = "COULD NOT FIND ERROR LABEL IN PROPERTIES";
	
	private static final double SCENE_WIDTH = 450;
	private static final double BORDERS = 30;
	private static final double BUTTON_WIDTH = 80;
	private static final double BUTTON_HEIGHT = 40;
	private static final double BUTTON_X = (SCENE_WIDTH - BUTTON_WIDTH)/2;
	
	
	private static final double DEFAULT_WIDTH = 50;
	private static final double DEFAULT_HEIGHT = 50;
	
	
	public DialogHandler(double width, double height){
		super(width, height);
		stage = new Stage();
		try{
			errorProperties = openPropertiesFile(PROPERTIES_LOCATION);
		}catch(Exception e){
			//nothing to do
		}
	}
	
	public DialogHandler() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	
	

	public void openPopup(String label)
	{
		String errorString = errorProperties.getProperty(label);	
		
		if ( errorString == null )
			errorString = ERROR;
		openDialog(errorString);
	}
	
	public void openPopup(String label, String arg1)
	{
		String errorString = errorProperties.getProperty(label);
		if ( errorString == null )
			errorString = ERROR;
		else 
			errorString = String.format(errorString, arg1);
		openDialog(errorString);
	}
	
	private void openDialog(String errorString)
	{
		root = new Group();
		errorPrompt = new Text(errorString);
		errorPrompt.setLayoutX((SCENE_WIDTH - errorPrompt.getBoundsInLocal().getWidth())/2);
		errorPrompt.setLayoutY(BORDERS);
		closeDialog = new Button(errorProperties.getProperty("errorButtonLabel"));
		closeDialog.setLayoutX(BUTTON_X);
		closeDialog.setLayoutY(errorPrompt.getLayoutY() + errorPrompt.getBoundsInLocal().getHeight() + BORDERS);
		closeDialog.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		closeDialog.setOnAction(e -> closeWindow());	
		root.getChildren().addAll(closeDialog, errorPrompt);
		scene = new Scene(root, SCENE_WIDTH, closeDialog.getLayoutY() + BUTTON_HEIGHT + BORDERS, Color.LIGHTGRAY);
		stage.setScene(scene);
		stage.show();
	}
	
	
	private void closeWindow()
	{
		stage.close();
	}

	@Override
	public Scene init() {
		return null;
	}


	@Override
	public void step(double elapsedTime) {
		
	}
	


	
	
}
