package frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class TurtlePanel extends Window {

	public TurtlePanel(Scene inScene, Display inDisplay) {
		super(600, 150);
	}

	// this will show the orientation, x, y, pen up/down, active, 
	
	@Override
	public Scene init() {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		
		return null;
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}
}
