package turtle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public interface State {
	public int getOrientation();
	public Point2D getLocation();
	public boolean isDown();
	public boolean isVisible();
	public Paint getPenColor();
	public double getLineWidth();	
}
