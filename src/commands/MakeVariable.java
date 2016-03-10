package commands;

import java.util.List;

import value.Value;

public class MakeVariable extends ControlCommand {
	Value variable;
	Value value;

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			variable = (Value) params.get(0);
			value = (Value)params.get(1);
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
