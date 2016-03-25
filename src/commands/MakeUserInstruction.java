package commands;		
		
import java.util.List;		
import value.VariableValue;		
		
public class MakeUserInstruction extends ControlCommand {		
 	private String functionName;		
 	private List<VariableValue> myVariables;		
 	private List<Command> myCommands;		
 			
 	public MakeUserInstruction() {	
 		setNumParams(2);
 	}		
 		
 	public void setFunctionName(String functionName) {
 		this.functionName = functionName;
 	}
 	
 	public String getFunctionName() {
 		return functionName;
 	}
 		
 	@Override		
 	protected void initParams(List<Object> params){		 				
 		myVariables = (List<VariableValue>) params.get(0);		
 		myCommands = (List<Command>) params.get(1);		
 	}		
 		
 	@Override		
 	public double evaluate() {		
 		if(functionName == null || !getEnvironment().getUserDefinedFunctions().containsKey(functionName)) {		
 			return 0;		
 		}		
 		UserDefinedFunction newFunction = getEnvironment().getUserDefinedFunctions().get(functionName);
 		newFunction.defineFunction(myVariables, myCommands);		
 		return 1;		
 	}		
 }