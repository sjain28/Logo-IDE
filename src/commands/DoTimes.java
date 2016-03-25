package commands;

import java.util.List;

import value.Value;
import value.VariableValue;

public class DoTimes extends BlockCommand {
	private VariableValue myVariable;
	private Value myLimit;
	private List<Command> commands;
		
	public DoTimes() {
		setNumParams(2);
	}

	@Override
	protected void initParams(List<Object> params){
			List<Object> arg1 = (List<Object>) params.get(0);
			myVariable = (VariableValue) arg1.get(0);
			myLimit = (Value) arg1.get(1);
			
			commands = (List<Command>) params.get(1);
	}

	@Override
	public double evaluate() {
		double value = 0;
		myVariable.setEnvironment(getEnvironment());
		int limit = myLimit.getValue().intValue();
		for (int i = 1; i <= limit; i++) {
			myVariable.setValue(i);
			for (int j = 0; j < commands.size(); j++) {
				value = commands.get(j).evaluate();
			}
		}
		setValue(value);
		return value;
	}

}
