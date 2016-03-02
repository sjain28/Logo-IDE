package commands;

import java.util.List;

public class IsShowing extends TurtleCommand{

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
		if(getTurtle().isVisible()){
			super.setValue(1);
			return 1;
		}
		super.setValue(0);
		return 0;
	}

}
