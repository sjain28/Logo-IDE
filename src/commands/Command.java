package commands;

import java.util.List;

import javafx.geometry.Point2D;

public abstract class Command {
	private int numParams;

	public abstract double evaluate();
	
	public int getNumParams(){
		return numParams;
	}
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != numParams){
			throw new Exception();
		}
	}
	
	protected void setNumParams(int num){
		numParams = num;
	}
}	
