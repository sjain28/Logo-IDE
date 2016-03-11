package commands;

import java.util.List;

import value.DoubleOptional;

public class DoTimes extends BlockCommand {
	private DoubleOptional myVariable;
	private DoubleOptional myLimit;
	private List<Command> commands;
		
	public DoTimes() {
		setNumParams(2);
	}

	@Override
	protected void initParams(List<Object> params){
			List<Object> arg1 = (List<Object>) params.get(0);
			myVariable = (DoubleOptional) arg1.get(0);
			myLimit = (DoubleOptional) arg1.get(1);
			
			commands = (List<Command>) params.get(1);
	}

	@Override
	public double evaluate() {
		double value = 0;
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
