package commands;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import turtle.Turtle;
import value.Value;

public class Tell extends Command{
	private List<Value> turtleIndices; 
	
	public Tell(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		getEnvironment().getActiveTurtles().clear(); 
		int index = -1;
		int numTurtles = getEnvironment().getTurtles().size();
		for(Value d: turtleIndices){
			index = d.getValue().intValue() -1; //minus one because turtle 1 is in 0th position of turtle list
			if(index >= numTurtles){
				for(int i = numTurtles; i <= index; i++){
					getEnvironment().makeNewTurtle(new Turtle(0.0, new Point2D(0,0), true, true, Color.BLACK, 3.0, 0));					
				}
			}
			getEnvironment().addActiveTurtle(getEnvironment().getTurtles().get(d.getValue().intValue())); //getEnvironment().getGlobalActive()
		}
		setValue(index);
		return index;
	}
	
	@Override
	protected void initParams(List<Object> params){
		turtleIndices = (List<Value>) params.get(0);
	}

}
