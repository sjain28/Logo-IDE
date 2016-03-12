package frontend;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import turtle.Agent;
import turtle.State;

public class Display extends Window {
	
	private static final double TURTLE_WIDTH = 32;
	private static final double TURTLE_HEIGHT = 32;
	private static final int IMAGEVIEWSIZEBOUND = 4;
	private static final int LINESIZEBOUND = 8;
	Image defaultImage = new Image("resources/images/Spiny.png");
	
	Canvas myCanvas;
	GraphicsContext gc;
	private double width;
	private double height;
	private int lineSpacing;

	private Collection<Agent> myTurtles;
	private Point2D ORIGIN = new Point2D(0,0);
	private double multFactor;
	private Map<Agent, ImageView> agentToPicture = new HashMap<Agent, ImageView>();
		
	public Display(double width, double height, int lineSpacing) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.lineSpacing = lineSpacing;
		myCanvas = new Canvas(width, height);
		gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);

		drawGrid(lineSpacing);
		
		ORIGIN = new Point2D(width/2 -TURTLE_WIDTH/2, height/2-TURTLE_HEIGHT/2);
		
	}
//=================================================================================
	@Override
	public Scene init() {
		multFactor = 1;
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight());
		super.getRoot().getChildren().add(myCanvas);
		return myScene;
	}
//================================================================================
	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub

		super.getRoot().getChildren().removeAll(agentToPicture.values());
		super.getRoot().getChildren().addAll(agentToPicture.values());
		
		setBackgroundColor(getController().getBackgroundColor());
		drawTurtle();

	}
//================================================================================
	public Canvas getCanvas() {
		return myCanvas;
	}
//================================================================================
	public void setBackgroundColor(Paint color) {
		gc.setFill(color);
		gc.clearRect(0, 0, width, height);
		gc.fillRect(0, 0, width, height);
		drawGrid(lineSpacing);
	}
//================================================================================
	// might make this private
	private void drawGrid(int lineSpacing) {		
		Paint saveCol = gc.getStroke();
		gc.setStroke(Color.GREY);
		// draw vertical lines
		if(lineSpacing/multFactor > LINESIZEBOUND){
			for (double i = 0; i < width; i += lineSpacing/multFactor) {
				gc.strokeLine(i, 0, i, height);
			}
			for (double i = 0; i < height; i += lineSpacing/multFactor) {
				gc.strokeLine(0, i, width, i);
			}
		}
		gc.setStroke(saveCol);
	}
//===================================================================================
	public void setImage(Image image) {
		Collection<Agent> myActiveTurtles = getController().getActiveTurtles();
		for(Agent myTurtle: myActiveTurtles){
			myTurtle.setShape(getController().getPictures().getItems().size()-1);
		}

	}
//=================================================================================	
	private void drawTurtle() {
		boolean wasOutOfBounds = false;
		myTurtles = getController().getAllTurtles();
		
		for( Agent thisTurtle: myTurtles){
			ImageView thisImageView;
			if(agentToPicture.containsKey(thisTurtle)){
				thisImageView = agentToPicture.get(thisTurtle);
			} else{
				thisImageView = new ImageView(defaultImage);
				thisImageView.setFitWidth(TURTLE_WIDTH/multFactor);
				thisImageView.setFitHeight(TURTLE_HEIGHT/multFactor);
				agentToPicture.put(thisTurtle, thisImageView);
			}

			turtleLoop(thisTurtle);
			if( outOfBounds(thisImageView) ){
				wasOutOfBounds = true;
			} 
			setImageView(thisImageView, thisTurtle);
		}

		if(wasOutOfBounds){
			adjustGridScale();
		}
	}
//---------------------------------------------------------------------------------
	private void setImageView(ImageView thisImageView, Agent thisTurtle){
		thisImageView.setImage(getController().getImage(thisTurtle.getShape()));
		thisImageView.setRotate(thisTurtle.getOrientation());
		thisImageView.setVisible(thisTurtle.isVisible());
		thisImageView.setX(imageViewOffsetX(thisTurtle.getLocation().getX(), thisImageView));
		thisImageView.setY(imageViewOffsetY(thisTurtle.getLocation().getY(), thisImageView));
	}
	
	private void adjustGridScale(){
		multFactor = multFactor*2;
		
		for(ImageView thisImageView: agentToPicture.values()){
			if( !(thisImageView.getFitWidth() < IMAGEVIEWSIZEBOUND || thisImageView.getFitHeight() < IMAGEVIEWSIZEBOUND) ){
				thisImageView.setFitWidth(thisImageView.getFitWidth()/2);
				thisImageView.setFitHeight(thisImageView.getFitHeight()/2);
			}
		}

	}
	
	private void turtleLoop(Agent thisTurtle){
		State prevT = null;
		double x1 = 0;
		double y1 = 0;

		for (State t : thisTurtle.getStates()) {
			if (prevT != null) {
				if (prevT.isDown() && t.isDown()) {
					x1 = lineOffsetX(prevT.getLocation().getX(), agentToPicture.get(thisTurtle));
					y1 = lineOffsetY(prevT.getLocation().getY(), agentToPicture.get(thisTurtle));
					double x2 = lineOffsetX(t.getLocation().getX(), agentToPicture.get(thisTurtle));
					double y2 = lineOffsetY(t.getLocation().getY(), agentToPicture.get(thisTurtle));
					gc.setLineWidth(t.getLineWidth());
					gc.setStroke(t.getPenColor());
					gc.strokeLine(x1, y1, x2, y2);
				}
			}
			prevT = t;
		}
	}
//===============================================================================	
	private double imageViewOffsetX(double inVal, ImageView thisImageView){
		return (inVal/multFactor + ORIGIN.getX() + thisImageView.getFitWidth() - thisImageView.getFitWidth()/multFactor);
	}
	
	private double lineOffsetX(double inVal, ImageView thisImageView){
		return (inVal/multFactor + ORIGIN.getX() + thisImageView.getFitWidth()/2 + thisImageView.getFitWidth() - thisImageView.getFitWidth()/multFactor);
	}
	
	private double imageViewOffsetY(double inVal, ImageView thisImageView){
		return (-1*inVal/multFactor + ORIGIN.getY() + thisImageView.getFitHeight() - thisImageView.getFitHeight()/multFactor);
	}
	
	private double lineOffsetY(double inVal, ImageView thisImageView){
		return (-1*inVal/multFactor + ORIGIN.getY() + thisImageView.getFitHeight()/2 + thisImageView.getFitHeight() -thisImageView.getFitHeight()/multFactor);
	}
//==================================================================================
	private boolean outOfBounds(ImageView thisImageView){
		return thisImageView.getX() < 0 || thisImageView.getX() > width ||
				thisImageView.getY() < 0 || thisImageView.getY() > height;
	}
	
	

}

