package commands;

import java.util.List;

import parser.CommandNode;
import parser.DoubleOptional;

public abstract class Command {
	public DoubleOptional myValue = new DoubleOptional(); //CHANGE THIS
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != getNumParams()){
			throw new Exception(); //Incorrect Number of Parameters Exception
		}
		initParams(params);
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



