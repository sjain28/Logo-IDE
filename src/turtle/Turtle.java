package turtle;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public class Turtle implements Agent{

	private double myOrientation;
	private Point2D myLocation;
	private boolean penIsDown;
	private boolean isVisible;
	private Paint myPenColor;
	private double myLineWidth;
	private int myTime;
	
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
		//myStates.add(copy());
	}

	public Turtle copy() {
		return new Turtle(myOrientation, myLocation, penIsDown, isVisible, myPenColor, myLineWidth, myTime);
	}
	
	@Override
	public void turn(double degrees) {
		myOrientation += degrees;
		addState();
	}

	@Override
	public void move(double distance) { //Orientation of 0 is NORTH
		double newX = myLocation.getX() + Math.sin(90-myOrientation); //Test to make sure this returns correct coordinates
		double newY = myLocation.getY() + Math.cos(90-myOrientation);
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
		// tangent = dy/dx
		double dy = position.getY() - myLocation.getY();
		double dx = position.getX() - myLocation.getX();
		double newOrient =90- Math.atan(dy/dx); //arctan won't work...it's in radians and only between += pi/2. Find a better way. 
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



}
