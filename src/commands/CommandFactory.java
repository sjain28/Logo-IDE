package commands;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

public class CommandFactory {
	private ResourceBundle myResourceBundle;
	
	public CommandFactory(ResourceBundle myResourceBundle) {
		this.myResourceBundle = myResourceBundle;
	}

	public Command makeCommand(String commandName) throws Exception {
		String command = "commands.";

		for(String key: myResourceBundle.keySet()){
			if(myResourceBundle.getString(key).contains("|"+commandName) || myResourceBundle.getString(key).contains(commandName+"|")){ //Do this with RegEx...This is messy
				command += key;
				System.out.println(command);
				Class commandClass = Class.forName(command);
				Constructor commandConstructor = commandClass.getConstructors()[0];
				Command result = (Command) commandConstructor.newInstance();
				return result;
			}
		}
		
		throw new Exception();
	}
}
