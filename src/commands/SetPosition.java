package commands;

import java.util.List;

import javafx.geometry.Point2D;
import parser.DoubleOptional;

public class SetPosition extends TurtleCommand{

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
		}catch(Exception e){
			throw new Exception();
		}
		
	}

	@Override
	public double evaluate() {
		Point2D nextLoc = new Point2D(x.getValue(),y.getValue());
		Point2D curLoc = getTurtle().getLocation();
		getTurtle().setLocation(nextLoc);
		
		double dx = nextLoc.getX()-curLoc.getX();
		double dy = nextLoc.getY()-curLoc.getY();
		double result = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		super.setValue(result);
		return result;
	}

}
