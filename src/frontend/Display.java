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
import turtle.Agent;
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

	Image defaultImage = new Image("resources/images/Spiny.png");
	private ImageView myImageView = new ImageView(defaultImage);
	private Collection<Turtle> myTurtles;
	private Point2D ORIGIN = new Point2D(0,0);
	final private Paint INITCOLOR = Color.BLACK;
	final private double DEFAULT_LINE_WIDTH = 2;
	private double multFactor = 1;
	private Turtle mainTurtle;

	public Display(double width, double height, int lineSpacing) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.lineSpacing = lineSpacing;
		myImageView.setFitHeight(TURTLE_WIDTH);
		myImageView.setFitWidth(TURTLE_HEIGHT);
		// myRect = new Rectangle(width, height, Color.WHITE);
		myCanvas = new Canvas(width, height);
		gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);

		drawGrid(lineSpacing);
		
		ORIGIN = new Point2D(width/2 -myImageView.getFitWidth()/2, height/2-myImageView.getFitHeight()/2);
//		mainTurtle = new Turtle(0, new Point2D(0,0),true, true, INITCOLOR, DEFAULT_LINE_WIDTH, 0);

	}

	@Override
	public Scene init() {
		mainTurtle = (Turtle)getController().getTurtle();
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight());
		super.getRoot().getChildren().add(myCanvas);
		super.getRoot().getChildren().add(myImageView);
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
		gc.setStroke(Color.GREY);
		// draw vertical lines
		if(lineSpacing/multFactor > 10){
			
			for (double i = 0; i < width; i += lineSpacing/multFactor) {
				gc.strokeLine(i, 0, i, height);
			}
			// draw horizontal lines
			for (double i = 0; i < height; i += lineSpacing/multFactor) {
				gc.strokeLine(0, i, width, i);
			}
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
		if (mainTurtle.getStates() != null) {

			for (State t : mainTurtle.getStates()) {

				
				if (prevT != null) {
					if (prevT.isDown() && t.isDown()) {
						
						x1 = drawOffsetX(prevT.getLocation().getX());
						y1 = drawOffsetY(prevT.getLocation().getY());
						double x2 = drawOffsetX(t.getLocation().getX());
						double y2 = drawOffsetY(t.getLocation().getY());
						gc.setStroke(t.getPenColor());
						gc.strokeLine(x1, y1, x2, y2);
					}
				}
				prevT = t;
			}
		}
		
		if( outOfBounds() ){
			multFactor = multFactor*2;
			System.out.println(multFactor);
			if( !(myImageView.getFitWidth() < 4 || myImageView.getFitHeight() < 4) ){
				myImageView.setFitWidth(myImageView.getFitWidth()/2);
				myImageView.setFitHeight(myImageView.getFitHeight()/2);
			}
		}
		
		if (mainTurtle != null) {
			myImageView.setRotate(mainTurtle.getOrientation());
			myImageView.setVisible(mainTurtle.isVisible());
			myImageView.setX(offsetX(mainTurtle.getLocation().getX()));
			myImageView.setY(offsetY(mainTurtle.getLocation().getY()));
		}
		else{
			myImageView.setX(offsetX(0));
		}
	}

	
	public Agent getTurtle() {
		return mainTurtle;
	}
	
	private double offsetX(double inVal){
		return (inVal/multFactor + ORIGIN.getX());
	}
	
	private double drawOffsetX(double inVal){
		return (inVal/multFactor + ORIGIN.getX() + myImageView.getFitWidth()/2);
	}
	
	private double offsetY(double inVal){
		return (-1*inVal/multFactor + ORIGIN.getY());
	}
	
	private double drawOffsetY(double inVal){
		return (-1*inVal/multFactor + ORIGIN.getY() + myImageView.getFitHeight()/2);
	}

	private boolean outOfBounds(){
		return myImageView.getX() < 0 || myImageView.getX() > width ||
				myImageView.getY() < 0 || myImageView.getY() > height;
	}
}
