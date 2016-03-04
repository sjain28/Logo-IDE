package commands;

import parser.Parser;

public abstract class ControlCommand extends Command {
	private Parser myParser;
	
	public void setParser(Parser parser) {
		myParser = parser;
	}
	protected Parser getParser() {
		return myParser;
	}
	
	
	
}
