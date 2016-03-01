package turtle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public interface Agent extends State{
	/** Turn can take negative values of degrees, which would specify counterclockwise rotation. */
	public void turn(double degrees);
	/** Move can take negative distance values, which would specify backwards movement. */
	public void move(double distance);
	public void setOrientation(double newOrientation);
	public void setTowards(Point2D position);
	public void setLocation(Point2D newLocation);
	public void changeVisibility(boolean isVisible);
	public void changePenVisibility(boolean isDown);
	public void setPenColor(Paint p);
	public void setLineWidth(double width);
}
