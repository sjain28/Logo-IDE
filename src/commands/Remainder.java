package commands;

import java.util.List;

import parser.DoubleOptional;

public class Remainder extends Command{
	
	private DoubleOptional term1;
	private DoubleOptional term2;

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			term1 = (DoubleOptional) params.get(0);
			term2 = (DoubleOptional) params.get(1);
		}
		catch(Exception e){
			throw new Exception();
		}
		
	}

	@Override
	public double evaluate() {
		double result = term1.getValue()%term2.getValue();
		super.setValue(result);
		return result;
	}

}
