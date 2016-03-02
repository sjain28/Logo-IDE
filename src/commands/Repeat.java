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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			numTimes = (DoubleOptional) params.get(0);
			commands = ((List <Command>)params.get(1));
		}
		catch(Exception e){
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		double value = 0;
		double n = numTimes.getValue();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < commands.size(); j++) {
				value = commands.get(j).evaluate();
			}
		}
		setValue(value);
		return value;
	}

}
