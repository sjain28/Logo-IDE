package frontend;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class PastCommands extends Window {
	
	private static ObservableList<String> previousCommands;

	public PastCommands(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Scene init() {
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.RED);
		
		 previousCommands = FXCollections.observableArrayList();
		 ListView<String> listView = new ListView<String>(previousCommands);
		
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
