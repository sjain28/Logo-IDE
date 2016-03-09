package parser;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.ResourceBundle;

import commands.Command;
import commands.UserDefinedFunction;
import frontend.ErrorHandler;

public class CommandFactory {
	private ResourceBundle myResourceBundle;
	private Map<String, UserDefinedFunction> UserDefinedFunctions;
	private Parser myParser;
	
	public CommandFactory(ResourceBundle myResourceBundle, Parser parser) {
		this.myResourceBundle = myResourceBundle;
		myParser = parser;
	}
	
//	public CommandFactory(ResourceBundle myResourceBundle, Map<String, UserDefinedFunction> UserDefinedFunctions) {
//		this.myResourceBundle = myResourceBundle;
//		this.UserDefinedFunctions = UserDefinedFunctions;
//	}
		
	public Command makeCommand(String name) throws Exception {
		updateUserDefinedFunctions();
		String command = "commands.";
		String commandName = name.toLowerCase();
			
		for(String key: myResourceBundle.keySet()){
			String value = myResourceBundle.getString(key);
			if(value.equals(commandName) || value.contains(commandName + " ") || value.contains(" "+commandName) || value.contains("\\" + commandName)|| value.contains("|"+commandName) || value.contains(commandName+"|")){ //Do this with RegEx...This is messy
				command += key;
				Class commandClass = Class.forName(command);
				Constructor commandConstructor = commandClass.getConstructors()[0];
				Command result = (Command) commandConstructor.newInstance();
				System.out.println(command);
				return result;
			}
		}
		if (UserDefinedFunctions != null) {
			for(String functionName : UserDefinedFunctions.keySet()) {
				if (name.equals(functionName)) {
					return UserDefinedFunctions.get(functionName);
				}
			}
		}
		
//		System.out.println(commandName);
		ErrorHandler eh = new ErrorHandler(50, 50);
		eh.init();
		eh.openError("IncorrectCommandException");	
		throw new Exception();
	}
	
	private void updateUserDefinedFunctions() {
		UserDefinedFunctions = myParser.getFunctions();
	}
}
