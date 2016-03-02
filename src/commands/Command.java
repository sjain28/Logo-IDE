package commands;

import java.util.List;

import frontend.ErrorHandler;
import parser.CommandNode;
import parser.DoubleOptional;

public abstract class Command {
	private DoubleOptional myValue = new DoubleOptional(); 
	
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
	
	public abstract int getNumParams();
	protected abstract void initParams(List<Object> params) throws Exception; //mandatory error checking for initialization
	public abstract double evaluate();
	
	protected void setValue(double value){
		if(myValue.getValue() == null){
			myValue.setValue(value);
		}
	}
	
	public DoubleOptional getValue(){
		return myValue; 
	}
}



