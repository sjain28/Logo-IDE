package commands;

import java.util.List;

import parser.DoubleOptional;

public class Product extends Command{

	private DoubleOptional first;
	private DoubleOptional second;
	
	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			first = (DoubleOptional) params.get(0);
			second = (DoubleOptional) params.get(1);
		}
		catch(Exception e){
			throw new Exception();
		}		
	}

	@Override
	public double evaluate() {
		double result = first.getValue()*second.getValue();
		super.setValue(result);
		return result;
	}

}
