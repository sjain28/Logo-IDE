package commands;

import javafx.scene.paint.Color;
import turtle.Agent;

public class GetPenColor extends TurtleCommand{
	
	public GetPenColor(){
		setNumParams(0);
	}
	
	@Override
	public double doCommand(Agent t) {
		int index = getController().getColorIndex((Color) t.getPenColor());
		setValue(index);
		return index;
	}

}
