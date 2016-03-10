package commands;

import java.util.List;

import parser.DoubleOptional;

public class Repeat extends ControlCommand {
	private DoubleOptional numTimes;
	private List<Command> commands;
	
	
	public Repeat() {
	}

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params){
			numTimes = (DoubleOptional) params.get(0);
			commands = ((List <Command>)params.get(1));
	}

	@Override
	public double evaluate() {
		double value = 0;
		int n =  numTimes.getValue().intValue();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < commands.size(); j++) {
				value = commands.get(j).evaluate();
			}
		}
		setValue(value);
		return value;
	}

}
