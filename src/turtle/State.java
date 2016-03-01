package turtle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/**
 *	Immutable object to represent Turtle data at some point in time.
 */
public interface State {
	public double getOrientation();
	public Point2D getLocation();
	public boolean isDown();
	public boolean isVisible();
	public Paint getPenColor();
	public double getLineWidth();
	public int getTime(); // time starts at 0 and increments with each Turtle Action

}
