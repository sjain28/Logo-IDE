package commands;

import java.util.List;

public class XCor extends TurtleCommand{

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
		super.setValue(getTurtle().getLocation().getX());
		return getTurtle().getLocation().getX();
	}

}
