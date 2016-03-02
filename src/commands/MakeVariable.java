package commands;

import java.util.List;

import parser.DoubleOptional;

public class MakeVariable extends ControlCommand {
	DoubleOptional variable;
	DoubleOptional value;

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			variable = (DoubleOptional) params.get(0);
			value = (DoubleOptional)params.get(1);
		}
		catch(Exception e){
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		double newValue = value.getValue();
		variable.setValue(newValue);
		setValue(newValue);
		return newValue;
	}

}
