package commands;

public abstract class Command {
	private String commandName;
	
	public Command(String name) {
		commandName = name;
	}
	
	public abstract int evaluate();
		
	public String toString() {
		return commandName;
	}

	public abstract void addParams();

	public abstract int getNumParams();
}
