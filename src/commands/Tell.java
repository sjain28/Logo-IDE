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
		getParser().getActiveTurtles().clear();
		double index = -1;
		for(DoubleOptional d: turtleIndices){
			index = d.getValue();
			if((int) index >= getParser().getAllTurtles().size()){
				for(int i = getParser().getAllTurtles().size(); i < index; i++){
					getParser().addTurtle(new Turtle(0.0, new Point2D(0,0), true, true, Color.BLACK, 3.0, 0));	
				}
			}else{
				getParser().addActive((int) index);
			}
		}
		setValue(index);
		return index;
	}
	
	@Override
	protected void initParams(List<Object> params){
		turtleIndices = (List<DoubleOptional>) params.get(0);
	}

}
