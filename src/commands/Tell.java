package commands;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import parser.DoubleOptional;
import turtle.Turtle;

public class Tell extends Command{
	private List<DoubleOptional> turtleIndices; 
	
	public Tell(){
		setNumParams(1);
	}
	
	@Override
	public double evaluate() {
		getParser().getActiveTurtles().clear(); //Final implementation: Change getParser() to getEnvironment().getGlobalActive()
		int index = -1;
		int numTurtles = getParser().getAllTurtles().size();
		for(DoubleOptional d: turtleIndices){
			index = d.getValue().intValue() -1; //minus one because turtle 1 is in 0th position of turtle list
			if(index >= numTurtles){
				for(int i = numTurtles; i <= index; i++){
					getParser().addTurtle(new Turtle(0.0, new Point2D(0,0), true, true, Color.BLACK, 3.0, 0));	 //getParser().getGlobalAll().add(..)
				}
			}
			getParser().addActive(index); //getEnvironment().getGlobalActive()
		}
		setValue(index);
		return index;
	}
	
	@Override
	protected void initParams(List<Object> params){
		turtleIndices = (List<DoubleOptional>) params.get(0);
	}

}
