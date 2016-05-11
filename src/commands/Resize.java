package commands;

// Note: This is the project's current default behavior, which is to adjust the size of the world to have the turtle still remain inside view

public class Resize extends Command {

	public Resize() {
		setNumParams(0);
	}

	@Override
	public double evaluate() {
		getController().setAdjustBounds(true);
		return 0;
	}

}
