package commands;

import java.util.ArrayList;
import java.util.List;
import frontend.ErrorHandler;
import parser.DoubleOptional;

public abstract class Command {
	private DoubleOptional myValue = new DoubleOptional(); 
	private int numParams;
	private List<Object> myParams = new ArrayList<>();
	
	
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
	
	
	protected void initParams(List<Object> params) throws Exception{	
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
}



