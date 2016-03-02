package commands;

import java.util.List;

import parser.DoubleOptional;

public class Tangent extends Command{
	
	private DoubleOptional term1;

	@Override
	public int getNumParams() {
		return 1;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			term1 = (DoubleOptional) params.get(0);
		}
		catch(Exception e){
			throw new Exception();
		}
		
	}

	@Override
	public double evaluate() {
		double result = Math.tan(term1.getValue());
		super.setValue(result);
		return result;
	}

}
