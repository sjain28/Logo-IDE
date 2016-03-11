package frontend;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import turtle.State;

public class VariableStates extends Window {
	
	private static ObservableList<String> variableStates;
	
	private static Controller controller;
	
	public VariableStates(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene init() {
		 Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		 variableStates = FXCollections.observableArrayList();
		 ListView<String> listView = new ListView<String>(variableStates);
		
		 super.getRoot().getChildren().add(listView);
		 return myScene;
	}
	
	public ObservableList<String> getVariableStates() {
		return variableStates;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	

	@Override
	public void step(double elapsedTime) {
		variableStates.clear();
		for (String key : controller.getVariableStates().keySet()) {
			variableStates.add(key + " : " + controller.getVariableStates().get(key));
		}
	}

}
