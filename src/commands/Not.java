package commands;

import java.util.List;

import parser.DoubleOptional;

public class Not extends Command{
	
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
		int result;
		
		if(term1.getValue().equals(0))
			result = 1;
		else
			result = 0;
		
		super.setValue(result);
		return result;
	}

}
