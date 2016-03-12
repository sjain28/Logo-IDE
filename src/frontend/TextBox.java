package frontend;

import javax.xml.parsers.ParserConfigurationException;

import control.Controller;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
//import xml.XMLWriter;

public class TextBox extends Window{
	
	private PastCommands pastCommands;
	
	//private XMLWriter xmlWriter;
	
	public TextBox(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	
	public void setPastCommandBox(PastCommands pastCommands) {
		this.pastCommands = pastCommands;
	}
	

	@Override
	public Scene init() {
		
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		//Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setVgap(5);
		grid.setHgap(5);
		//Defining the Name text field
		final TextArea name = new TextArea();
		name.setPromptText("Enter Your Command:");
		name.setPrefColumnCount(20);
		name.setPrefRowCount(5);
		name.getText();
		GridPane.setConstraints(name, 0, 0);
		grid.getChildren().add(name);
		//Defining the Submit button
		
		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
				try {
					pastCommands.getPreviousCommands().add(name.getText());
					getController().makeParser(name.getText());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			 }
		});
		
		/*try {
			xmlWriter = new XMLWriter("/Users/bobby_mac/Documents/workspace/slogo_team17/test.txt", getController());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		Button write = new Button("Write XML");
		GridPane.setConstraints(write, 1, 1);
		grid.getChildren().add(write);
		//write.setOnAction(e -> xmlWriter.write());
		
		
		super.getRoot().getChildren().add(grid);
		return myScene;
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
