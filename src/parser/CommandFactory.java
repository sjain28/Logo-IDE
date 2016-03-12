package parser;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.ResourceBundle;

import commands.Command;
import commands.UserDefinedFunction;
import frontend.ErrorHandler;

public class CommandFactory {
	private final static String COMMAND_PACKAGE = "commands.";
	private ResourceBundle myResourceBundle;
	
	public CommandFactory(ResourceBundle myResourceBundle) {
		this.myResourceBundle = myResourceBundle;
	}
	
//	public CommandFactory(ResourceBundle myResourceBundle, Map<String, UserDefinedFunction> UserDefinedFunctions) {
//		this.myResourceBundle = myResourceBundle;
//		this.UserDefinedFunctions = UserDefinedFunctions;
//	}
		
	public Command makeCommand(String name, Environment env) throws Exception {
		String commandName = name.toLowerCase();
		String command = COMMAND_PACKAGE;
		Command result = null;
		Map<String, UserDefinedFunction> UserDefinedFunctions = env.getUserDefinedFunctions();
		for(String functionName : UserDefinedFunctions.keySet()) {
			if (name.equals(functionName)) {
				result = UserDefinedFunctions.get(functionName);
			}
		}
		for(String key: myResourceBundle.keySet()){
			String value = myResourceBundle.getString(key);
			if(commandName.matches(value)){
				command += key;
				Class<?> commandClass = Class.forName(command);
				Constructor<?> commandConstructor = commandClass.getConstructors()[0];
				result = (Command) commandConstructor.newInstance();
			}
		}
		if (result != null) {
			result.setEnvironment(env);
			return result;
		}

		throw new Exception();
	}
}
