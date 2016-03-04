package commands;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import frontend.ErrorHandler;

public class CommandFactory {
	private ResourceBundle myResourceBundle;
	
	public CommandFactory(ResourceBundle myResourceBundle) {
		this.myResourceBundle = myResourceBundle;
	}
		
	public Command makeCommand(String name) throws Exception {
		String command = "commands.";
		String commandName = name.toLowerCase();
			
		for(String key: myResourceBundle.keySet()){
			String value = myResourceBundle.getString(key);
			if(value.equals(commandName) || value.contains(commandName + " ") || value.contains(" "+commandName) || value.contains("\\" + commandName)|| value.contains("|"+commandName) || value.contains(commandName+"|")){ //Do this with RegEx...This is messy
				command += key;
				Class commandClass = Class.forName(command);
				Constructor commandConstructor = commandClass.getConstructors()[0];
				Command result = (Command) commandConstructor.newInstance();
				return result;
			}
		}
		ErrorHandler eh = new ErrorHandler(50, 50);
		eh.init();
		eh.openError("IncorrectCommandException");	
		throw new Exception();
	}
}
