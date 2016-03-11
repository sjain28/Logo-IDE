package commands;

import control.Controller;

public abstract class DisplayCommand extends Command{
	private Controller myController;
	
	@Override
	public double evaluate() {
		return doCommand(myController);
	}
	
	public void setController(Controller c){
		if(myController == null){
			myController = c;
		}
	}
	
	public abstract double doCommand(Controller c);

}
