package commands;

import java.util.ArrayList;
import java.util.List;

import control.Environment;
import frontend.ErrorHandler;
import parser.DoubleOptional;
import parser.Parser;

public abstract class Command {
	private DoubleOptional myValue = new DoubleOptional(); 
	private int numParams;
	private List<Object> myParams = new ArrayList<>();
	private Parser myParser; // To be changed to scope/environment class
	private Environment myEnvironment;
	
	public void setEnvironment(Environment e){
		myEnvironment = e;
	}
	
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
			myParams.add((DoubleOptional) params.get(i));
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
	
	public DoubleOptional getValue(){
		return myValue; 
	}
	
	public void setParser(Parser parser) {
		myParser = parser;
	}
	public Parser getParser() {
		return myParser;
	}
}



