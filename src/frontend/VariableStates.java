package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class VariableStates extends Window {
	
	private ObservableList<String> variableStates = FXCollections.observableArrayList();

	public VariableStates(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene init() {
		 Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		 ListView<String> listView = new ListView<>(variableStates);
		
		 super.getRoot().getChildren().add(listView);
		 return myScene;
	}
	
	public ObservableList<String> getVariableStates() {
		return variableStates;
	}
		

	@Override
	public void step(double elapsedTime) {
		System.out.println(getController().getVariables().size());
		variableStates.clear();
		for (String key : getController().getVariables().keySet()) {
			variableStates.add(key + " : " + getController().getVariables().get(key));
		}
	}

}
