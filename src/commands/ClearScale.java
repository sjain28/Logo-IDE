package commands;

public class ClearScale extends ControlCommand {
	
	public ClearScale() {
		super.setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		//System.out.println("SUCCESSFUL CLEAR");
		return 2;
	}

}
