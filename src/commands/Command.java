package commands;

import javafx.geometry.Point2D;

public abstract class Command {
	private String commandName;
	
	public Command(String name) {
		commandName = name;
	}
	
	public abstract int evaluate();
		
	public String toString() {
		return commandName;
	}

}
