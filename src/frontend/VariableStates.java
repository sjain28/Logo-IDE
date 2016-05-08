package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import turtle.Agent;

public class VariableStates extends Window {
	
	private ObservableList<String> variableStates = FXCollections.observableArrayList();
	private VBox myTurtleBox = new VBox();
	
	public VariableStates(double width, double height) {
		super(width, height);
	}

	@Override
	public Scene init() {
		 Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		 ListView<String> listView = new ListView<>(variableStates);
		
		 VBox toAdd = new VBox();
		 toAdd.getChildren().add(listView);
		 toAdd.getChildren().add(myTurtleBox);
		 
		 super.getRoot().getChildren().add(toAdd);
		 return myScene;
	}
	
	public ObservableList<String> getVariableStates() {
		return variableStates;
	}
		

	@Override
	public void step(double elapsedTime) {
		variableStates.clear();
		
		myTurtleBox.getChildren().clear();
		myTurtleBox.getChildren().addAll(super.getController().getTurtleAgents());
		
		for (String key : getController().getVariables().keySet()) {
			variableStates.add(key + " : " + getController().getVariables().get(key));
		}
	}

}
