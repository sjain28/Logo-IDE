package commands;

import java.util.List;

import value.Value;

public class For extends BlockCommand {
	private Value myVariable;
	private Value myStart;
	private Value myEnd;
	private Value myIncrement;
	private List<Command> myCommands;
		
	public For() {
		setNumParams(2);
	}

	@Override
	protected void initParams(List<Object> params) {
			List<Object> arg1 = (List<Object>) params.get(0);
			myVariable = (Value) arg1.get(0);
			myStart = (Value) arg1.get(1);
			myEnd = (Value) arg1.get(2);
			myIncrement = (Value) arg1.get(3);
			
			myCommands = (List<Command>) params.get(1);
	}

	@Override
	public double evaluate() {
		double value = 0;
		int start = myStart.getValue().intValue();
		int end = myEnd.getValue().intValue();
		int incr = myIncrement.getValue().intValue();
		
		for (int i = start; i < end; i += incr) {
			myVariable.setValue(i);
			for (int j = 0; j < myCommands.size(); j++) {
				value = myCommands.get(j).evaluate();
			}
		}
		setValue(value);
		return value;
	}

}
