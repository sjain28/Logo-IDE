package commands;

public class Pi extends Command{
	
	public Pi(){
		setNumParams(0);
	}
	
	@Override
	public double evaluate() {
		double result = Math.PI;
		super.setValue(result);
		return result;
	}

}
