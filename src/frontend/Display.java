package frontend;

import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import turtle.State;
import turtle.Turtle;

public class Display extends Window {

	private final static double TURTLE_WIDTH = 50;
	private static final double TURTLE_HEIGHT = 50;

	Canvas myCanvas;
	GraphicsContext gc;
	private double width;
	private double height;
	private int lineSpacing;
	private ImageView myImageView = new ImageView();
	private Collection<Turtle> myTurtles;
	final private Point2D ORIGIN = new Point2D(0, 0);
	final private Paint INITCOLOR = Color.BLACK;
	final private double DEFAULT_LINE_WIDTH = 2;
	private Turtle myActiveTurtle;

	public Display(double width, double height, int lineSpacing) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.lineSpacing = lineSpacing;
		myImageView.setFitHeight(50);
		myImageView.setFitWidth(50);
		// myRect = new Rectangle(width, height, Color.WHITE);
		myCanvas = new Canvas(width, height);
		gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);
		myActiveTurtle = new Turtle(0, ORIGIN, true, true, INITCOLOR, DEFAULT_LINE_WIDTH, 0);

		drawGrid(lineSpacing);

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
		setBackgroundColor(gc.getFill());
		drawTurtle();
		// image = turtle -> adjust x&y

	}

	public Canvas getCanvas() {
		return myCanvas;
	}

	public void setBackgroundColor(Paint color) {
		gc.setFill(color);
		gc.clearRect(0, 0, width, height);
		gc.fillRect(0, 0, width, height);
		drawGrid(lineSpacing);
	}

	public void drawGrid(int lineSpacing) {
		gc.setStroke(Color.BLACK);
		// draw vertical lines
		for (int i = 0; i < width; i += lineSpacing) {
			gc.strokeLine(i, 0, i, height);
		}
		// draw horizontal lines
		for (int i = 0; i < height; i += lineSpacing) {
			gc.strokeLine(0, i, width, i);
		}
	}

	public void setImage(Image image) {
		myImageView.setImage(image);
		// super.getRoot().getChildren().add(myImageView);

	}

	private void drawTurtle() {
		State prevT = null;
		double x1 = 0;
		double y1 = 0;
		// can be done better
		if (myActiveTurtle.getMyStates() != null) {

			for (State t : myActiveTurtle.getMyStates()) {

				if (prevT != null) {
					if (prevT.isDown() && t.isDown()) {
						x1 = prevT.getLocation().getX();
						y1 = prevT.getLocation().getY();
						double x2 = t.getLocation().getX();
						double y2 = t.getLocation().getY();
						gc.setStroke(t.getPenColor());
						gc.strokeLine(x1, y1, x2, y2);
					}
				}
				prevT = t;
			}
		}
		
		if (myActiveTurtle != null) {
			myImageView.setRotate(myActiveTurtle.getOrientation());
			if (myActiveTurtle.isVisible()) {
				// adjust for centering turtles
				gc.drawImage(myImageView.getImage(), x1, y1, TURTLE_WIDTH, TURTLE_HEIGHT);
			}
		}

	}
}
