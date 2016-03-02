package commands;

import java.util.List;

import parser.CommandNode;
import parser.DoubleOptional;

public abstract class Command {
	private DoubleOptional myValue = new DoubleOptional(); 
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != getNumParams()){
			throw new Exception(); //Incorrect Number of Parameters Exception
		}
		try{
			initParams(params);
		}
		catch(Exception e){
			
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



