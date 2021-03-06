package commands;

import java.util.List;

import value.Value;
import value.VariableValue;

public class MakeVariable extends ControlCommand {
	VariableValue variable;
	Value value;

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) {
			variable = (VariableValue) params.get(0);
			value = (Value) params.get(1);
	}

	@Override
	public double evaluate() {
		double newValue = value.getValue();
		variable.setValue(newValue);
		getEnvironment().setVariable(variable.getName(), newValue);
		setValue(newValue);
		return newValue;
	}

}
