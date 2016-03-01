package commands;

import java.util.List;

public class Heading extends TurtleCommand{

	@Override
	public int getNumParams() {
		return 0;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		return;		
	}

	@Override
	public double evaluate() {
		return getTurtle().getOrientation();
	}

}
