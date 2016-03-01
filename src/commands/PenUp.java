package commands;

import java.util.List;

public class PenUp extends TurtleCommand{

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
		getTurtle().changePenVisibility(false);
		super.setValue(0);
		return 0;
	}

}
