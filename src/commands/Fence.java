package commands;

import frontend.GUI;
import turtle.Agent;
import value.Value;

public class Fence extends TurtleCommand{
	
	public Fence(){
		setNumParams(1);
	}
	
	@Override
	public double doCommand(Agent t) {
		Value distance = (Value) super.getParams().get(0);
		
		double distanceToEdgeX =( GUI.DISPLAY_WIDTH/2-Math.abs(t.getLocation().getX()))*Math.cos(t.getOrientation());
		double distanceToEdgeY = (GUI.DISPLAY_HEIGHT/2 - Math.abs(t.getLocation().getY()))*Math.sin(t.getOrientation());
		
		double distanceToEdge = Math.sqrt(Math.pow(distanceToEdgeX, 2) + Math.pow(distanceToEdgeY, 2));
		t.move(Math.min(distance.getValue(), distanceToEdge));
		return 3;
	}

}
