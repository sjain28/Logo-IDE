package commands;

import java.util.List;

import parser.DoubleOptional;
import parser.DoubleOptional;

public abstract class Command {
	private int numParams = -1;	
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != numParams){
			throw new Exception(); //Incorrect Number of Parameters Exception
		}
		initParams(params);
	}
	
	public abstract int getNumParams();
	protected abstract void initParams(List<Object> params) throws Exception; //mandatory error checking for initialization
	public abstract double evaluate();

}



