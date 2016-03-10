package commands;

import java.util.ArrayList;
import java.util.List;
import frontend.ErrorHandler;
import parser.Parser;
import value.Value;

public abstract class Command {
	private Value myValue = new Value(); 
	private int numParams;
	private List<Object> myParams = new ArrayList<>();
	private Parser myParser; // To be changed to scope/environment class
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != getNumParams()){
			ErrorHandler eh = new ErrorHandler(50, 50);
			eh.init();
			eh.openError("NumParamsException");	
		}
		try{
			initParams(params);
		}
		catch(Exception e){
			ErrorHandler eh = new ErrorHandler(50, 50);
			eh.init();
			eh.openError("InvalidInputException");
		}
	}
	
	
	protected void initParams(List<Object> params){	
		for(int i = 0; i < numParams; i++){
			myParams.add((Value) params.get(i));
		}
	}
	
	protected List<Object> getParams(){
		return myParams;
	}
	
	public abstract double evaluate();
	
	public int getNumParams(){
		return numParams;
	}

	protected void setValue(double value){
		if(myValue.getValue() == null){
			myValue.setValue(value);
		}
	}
	
	protected void setNumParams(int params){
		numParams = params;
	}
	
	public Value getValue(){
		return myValue; 
	}
	
	
	public void setParser(Parser parser) {
		myParser = parser;
	}
	protected Parser getParser() {
		return myParser;
	}
}



