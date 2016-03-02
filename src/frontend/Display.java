package frontend;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import turtle.Agent;
import turtle.Turtle;

public class Display  extends Window{

	Canvas myCanvas;
	GraphicsContext gc;
	private double width;
	private double height;
	private int lineSpacing;
	private Turtle mainTurtle;
	
	public Display(double width, double height, int lineSpacing) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.lineSpacing = lineSpacing;
		//myRect = new Rectangle(width, height, Color.WHITE);
		myCanvas = new Canvas(width, height);
		gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);
		
		drawGrid(lineSpacing);
		
		
		mainTurtle = new Turtle(0, new Point2D(0,0),true, true, null, 1, 0);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene init() {
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight());
		return myScene;
	}
	

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
	}
	
	
	public Canvas getCanvas() {
		return myCanvas;
	}
	
	public void setBackgroundColor(Color color) {
		gc.setFill(color);
		gc.clearRect(0, 0, width, height);
		gc.fillRect(0, 0, width, height);
		drawGrid(lineSpacing);
	}
	
	public void drawGrid(int lineSpacing) {
		gc.setStroke(Color.BLACK);
		//draw vertical lines
		for (int i = 0; i<width; i+=lineSpacing) {
			gc.strokeLine(i, 0, i, height);
		}
		//draw horizontal lines
		for (int i = 0; i<height; i+=lineSpacing) {
			gc.strokeLine(0, i, width, i);
		}
	}
	
	public Agent getTurtle() {
		return mainTurtle;
	}
	
}
