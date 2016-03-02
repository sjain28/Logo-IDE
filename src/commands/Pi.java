package commands;

import java.util.List;

import parser.DoubleOptional;

public class Pi extends Command{
	
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
		double result = Math.PI;
		super.setValue(result);
		return result;
	}

}
