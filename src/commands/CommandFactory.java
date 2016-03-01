package commands;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

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
		System.out.println(commandName);
		throw new Exception();
	}
}
