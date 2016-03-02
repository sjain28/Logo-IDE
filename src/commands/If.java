package commands;

import java.util.List;

import parser.DoubleOptional;

public class If extends ControlCommand {
	private DoubleOptional myExpr;
	private List<Command> myCommands;
	
	public If() {
	}

	@Override
	public int getNumParams() {
		return 2;
	}

	@Override
	protected void initParams(List<Object> params) throws Exception {
		try{
			myExpr = (DoubleOptional) params.get(0);
			myCommands = (List<Command>) params.get(1);
		}
		catch(Exception e){
			throw new Exception();
		}
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