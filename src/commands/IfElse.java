package commands;

import java.util.List;

import value.Value;

public class IfElse extends ControlCommand {
	private Value myExpr;
	private List<Command> myIfCommands;
	private List<Command> myElseCommands;

	public IfElse() {
	}

	@Override
	public int getNumParams() {
		return 3;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			myExpr = (Value) params.get(0);
			myIfCommands = (List<Command>) params.get(1);
			myElseCommands = (List<Command>) params.get(2);
		}
		catch(Exception e){
			throw new Exception();
		}
	}

	@Override
	public double evaluate() {
		double expr = myExpr.getValue();
		if (expr != 0) {
			for (int i = 0; i < myIfCommands.size(); i++) {
				expr = myIfCommands.get(i).evaluate();
			}
		}
		else {
			expr = 0;
			for (int i = 0; i < myElseCommands.size(); i++) {
				expr = myElseCommands.get(i).evaluate();
			}
		}
		return expr;
	}

}
