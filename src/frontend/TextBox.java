package frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import parser.Parser;

public class TextBox extends Window{
	
	public TextBox(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public Scene init() {
		
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.BLACK);
		
		//Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		//Defining the Name text field
		final TextArea name = new TextArea();
		name.setPromptText("Enter Your Command:");
		name.setPrefColumnCount(20);
		name.setPrefRowCount(10);
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
			        System.out.println(name.getText());
			 }
		});
		super.getRoot().getChildren().add(grid);
		return myScene;
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
