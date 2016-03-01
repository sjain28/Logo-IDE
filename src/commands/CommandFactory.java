package commands;

import java.util.List;
import java.util.ResourceBundle;

public class CommandFactory {
	private ResourceBundle myResourceBundle;
	
	public CommandFactory(ResourceBundle myResourceBundle) {
		this.myResourceBundle = myResourceBundle;
	}

	public Command makeCommand(String _commandName) throws Exception {
		String commandName = _commandName;
		if (commandName.equals(myResourceBundle.getString("Forward"))) {
			System.out.println("Forward recognized");
			return null;
		}
		else if (false) {
			return null;
		}
		else {
			throw new Exception(commandName);
			//throw new InvalidCommandException(commandName);
		}
	}
}
