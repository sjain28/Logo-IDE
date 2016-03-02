package commands;

import java.util.List;

import parser.DoubleOptional;

public class Or extends Command{
	
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
		int result;
		
		if(term1.getValue().equals(0.0) && term2.getValue().equals(0.0))
			result = 0;
		else
			result = 1;
		
		super.setValue(result);
		return result;
	}

}
