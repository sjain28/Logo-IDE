package frontend;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class VariableStates extends Window {
	
	private static ObservableList<String> variableStates;
	
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
		super.setController(controller);
	}
	

	@Override
	public void step(double elapsedTime) {
		variableStates.clear();
		for (String key : super.getController().getVariables().keySet()){
			variableStates.add(key + " : " + getController().getVariables().get(key));
		}
	}

}
