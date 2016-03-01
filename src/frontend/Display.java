package frontend;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Display  extends Window{

	public Display(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene init() {
		// TODO Auto-generated method stub
		return new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.BLACK);
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
	}
	
}
