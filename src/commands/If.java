package commands;

import java.util.List;

import value.Value;

public class If extends ControlCommand {
	private Value myExpr;
	private List<Command> myCommands;
	
	public If() {
	}

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) {
			myExpr = (Value) params.get(0);
			myCommands = (List<Command>) params.get(1);
	}

	@Override
	public double evaluate() {
		double expr = myExpr.getValue();
		if (expr == 0) {
			setValue(0);
		}
		else {
			for (int i = 0; i < myCommands.size(); i++) {
				expr = myCommands.get(i).evaluate();
			}
			setValue(expr);
		}
		return expr;
	}

}
