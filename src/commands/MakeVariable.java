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
	protected void initParams(List<Object> params)  {
			variable = (DoubleOptional) params.get(0);
			value = (DoubleOptional)params.get(1);
		
	}

	@Override
	public double evaluate() {
		double newValue = value.getValue();
		variable.setValue(newValue);
		setValue(newValue);
		return newValue;
	}

}
