package commands;

public class SetScale extends ControlCommand{
	
	public SetScale() {
		super.setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		//System.out.println("SUCCESSFUL SET");
		return 1;
	}

}
