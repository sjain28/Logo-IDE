package commands;

import java.util.List;

public class PenDownQuery extends TurtleCommand{

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
		if(getTurtle().isDown()){
			super.setValue(1);
			return 1;
		}	
		super.setValue(0);
		return 0;
	}	
}
