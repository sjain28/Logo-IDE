package commands;

import java.util.List;

public class ShowTurtle extends TurtleCommand{

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
		getTurtle().changeVisibility(true);
		super.setValue(1);
		return 1;
	}

}
