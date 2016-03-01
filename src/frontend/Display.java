package frontend;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Display  extends Window{

	Rectangle myRect;
	
	public Display(double width, double height) {
		super(width, height);
		myRect = new Rectangle(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene init() {
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight(), Color.BLACK);
		return myScene;
	}
	

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
	}
	
	public Rectangle getRectangle(){
		return myRect;
	}
}
