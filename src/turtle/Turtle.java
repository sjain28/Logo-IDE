package turtle;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Turtle implements Agent{

	private double myOrientation;
	private Point2D myLocation;
	private boolean penIsDown;
	private boolean isVisible;
	private Paint myPenColor;
	private double myLineWidth;
	private int myTime;
	private Turtle startingState;
	private int myShape;
	
	private List<Turtle> myStates;
	
	public Turtle(double orient, Point2D loc, boolean down, boolean visible, Paint pen, double line, int curTime) {
		myOrientation = orient;
		myLocation = loc;
		penIsDown = down;
		isVisible = visible;
		myPenColor = pen;
		myLineWidth = line;
		myTime = curTime;
		myStates = new ArrayList<Turtle>();
		myShape = -1;
	}
	
	public void init(){
		startingState = copy();
		myStates.add(startingState);
	}

	private Turtle copy() {
		return new Turtle(myOrientation, myLocation, penIsDown, isVisible, myPenColor, myLineWidth, myTime);
	}
	
	@Override
	public void turn(double degrees) {
		myOrientation += degrees;
		addState();
	}

	@Override
	public void move(double distance) { //Orientation of 0 is NORTH
		double newX = myLocation.getX() + Math.sin((myOrientation)*Math.PI/180)*distance; //Tested and working...orientation of 0 moves straight up
		double newY = myLocation.getY() + Math.cos((myOrientation)*Math.PI/180)*distance;
		myLocation = new Point2D(newX, newY);
		addState();
	}

	@Override
	public void setOrientation(double newOrientation) {
		myOrientation = newOrientation;
		addState();
	}

	@Override
	public void setTowards(Point2D position) {

		double newOrient;
		double dy = position.getY() - myLocation.getY();
		double dx = position.getX() - myLocation.getX();
		
		if(dx == 0 && dy == 0){
			newOrient = 0;
		}
		else if(dx == 0){
			newOrient = 90-90*Math.signum(dy);
		}
		else if(dy == 0){
			newOrient = 180 -90*Math.signum(dx);
		}
		else{
			newOrient = Math.atan(dy/dx)*180/Math.PI; 
		}
		this.setOrientation(newOrient);		
	}

	@Override
	public void setLocation(Point2D newLocation) {
		myLocation = newLocation;
		addState();
	}

	@Override
	public void changeVisibility(boolean visible) {
		isVisible = visible;
		addState();
	}

	@Override
	public void changePenVisibility(boolean isDown) {
		penIsDown = isDown;
		addState();
	}

	private void addState() {
		myTime++;
		myStates.add(copy());
	}

	@Override
	public void setPenColor(Paint p) {
		this.myPenColor = p;
		addState();
		
	}

	@Override
	public void setLineWidth(double width) {
		myLineWidth = width;
		System.out.println("-----------" + myLineWidth);
		addState();
	}

	@Override
	public double getOrientation() {
		return myOrientation;
	}

	@Override
	public Point2D getLocation() {
		return myLocation;
	}

	@Override
	public boolean isDown() {
		return this.penIsDown;
	}
	
	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public Paint getPenColor() {
		return myPenColor;
	}

	@Override
	public double getLineWidth() {
		return myLineWidth;
	}

	@Override
	public int getTime() {
		return myTime;
	}
	@Override
	public Iterable<State> getStates(){
		return (Iterable) myStates;
	}

	public void clear(){
		this.setLocation(new Point2D(0,0));
		myStates = new ArrayList<>();
		this.setOrientation(0);
	}
	
	public int getShape(){
		return myShape;
	}
	
	public void setShape(int s){
		myShape = s;
	}
}
