package commands;

import java.util.List;

import javafx.geometry.Point2D;
import parser.DoubleOptional;

public class SetTowards extends TurtleCommand{
	
	private DoubleOptional x;
	private DoubleOptional y;
	
	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			x = (DoubleOptional) params.get(0);
			y = (DoubleOptional) params.get(1);
		}
		catch(Exception e){
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		double first = super.getTurtle().getOrientation();
		Point2D toward = new Point2D(x.getValue(), y.getValue());
		getTurtle().setTowards(toward);
		double second = getTurtle().getOrientation();
		super.setValue(second-first);
		return second - first;
	}

}
