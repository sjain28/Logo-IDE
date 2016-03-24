// This entire file is part of my masterpiece.
// Bobby Wang

package frontend;

import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import xml.XMLWriter;

// there be magic in this one
public class TextBox extends Window{
	
	private static final String WRITER_LOCATION = "test.txt";
	
	private static final String LABEL_LOCATION = "resources/labels/UILabels.properties";

	private PastCommands pastCommands;
	
	private XMLWriter xmlWriter;
	
	private Properties buttonLabels;
	
	
	private GridPane grid;
	
	public TextBox(double width, double height) {
		super(width, height);
		
		try {
			buttonLabels = openPropertiesFile(LABEL_LOCATION);
		} catch (Exception e) {
			DialogHandler dh = new DialogHandler();
			dh.init();
			dh.openPopup("BadPropertiesLocation");
		}
		
	}
	
	
	public void setPastCommandBox(PastCommands pastCommands) {
		this.pastCommands = pastCommands;
	}
	

	@Override
	public Scene init() {
		
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		setupGrid();
		
		TextArea commandInput = new TextArea();
		
		formatInputArea(commandInput);
		createSubmissionButton(commandInput);
		createXMLButton();
		
		super.getRoot().getChildren().add(grid);
		return myScene;
	}
	
	private void setupGrid() {
		grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setVgap(5);
		grid.setHgap(5);
	}
	
	
	private void formatInputArea(TextArea commandInput) {
		commandInput.setPromptText(buttonLabels.getProperty("defaultInputPrompt"));
		commandInput.setPrefColumnCount(20);
		commandInput.setPrefRowCount(5);
		commandInput.getText();
		GridPane.setConstraints(commandInput, 0, 0);
		grid.getChildren().add(commandInput);
	}

	private void createSubmissionButton(TextArea commandInput) {
		
		Button submit = new Button(buttonLabels.getProperty("submitButton"));
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
				try {
					pastCommands.getPreviousCommands().add(commandInput.getText());
					getController().makeParser(commandInput.getText());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			 }
		});
	}
	
	private void createXMLButton() {
		
		try {
			xmlWriter = new XMLWriter(WRITER_LOCATION, getController());
		} catch (ParserConfigurationException e) {
			DialogHandler dh = new DialogHandler();
			dh.init();
			dh.openPopup("BadWriterLocation");
		}
		
		Button write = new Button(buttonLabels.getProperty("writeXML"));
		GridPane.setConstraints(write, 1, 1);
		grid.getChildren().add(write);
		write.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                xmlWriter.write();
                DialogHandler dh = new DialogHandler();
                dh.init();
                dh.openPopup("SuccessfulSave");
            }
        });
	}
	
	@Override
	public void step(double elapsedTime) {
		//left here because textbox may need dynamic animation in the future, like other Window subclasses
	}

}
