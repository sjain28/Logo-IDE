package commands;

public class Window extends Command {

	public Window() {
		setNumParams(0);
	}

	@Override
	public double evaluate() {
		getController().setAdjustBounds(false);
		return 2;
	}

}
