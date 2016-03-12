package frontend;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class PastCommands extends Window {
	
	private ObservableList<String> previousCommands = FXCollections.observableArrayList();

	public PastCommands(double width, double height) {
		super(width, height);

	}
	
	@Override
	public Scene init() {
		 Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		 ListView<String> listView = new ListView<>(previousCommands);
		
		 super.getRoot().getChildren().add(listView);
		
		 return myScene;
	}
	
	public ObservableList<String> getPreviousCommands() {
		return previousCommands;
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
