package commands;

public class IsShowing extends TurtleCommand{

	public IsShowing(){
		setNumParams(0);
	}

	@Override
	public double evaluate() {
		if(getTurtle().isVisible()){
			super.setValue(1);
			return 1;
		}
		super.setValue(0);
		return 0;
	}

}
