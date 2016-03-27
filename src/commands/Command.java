package commands;

import java.util.ArrayList;
import java.util.List;

import control.Controller;
import frontend.DialogHandler;
import parser.Environment;
import value.Value;
import value.NumericalValue;

public abstract class Command {
	public final static int SIZE = 50;
	
	
	private Value myValue = new NumericalValue(); 
	private int numParams;
	private List<Value> myParams;
	private Environment myEnvironment;
	private Controller myController;
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != getNumParams()){
			DialogHandler eh = new DialogHandler(SIZE, SIZE);
			eh.init();
			eh.openPopup("NumParamsException");	
		}
		try{
			initParams(params);
		}
		catch(Exception e){
			DialogHandler eh = new DialogHandler(SIZE, SIZE);
			eh.init();
			eh.openPopup("InvalidInputException");
		}
	}
	
	protected void initParams(List<Object> params){	
		myParams = new ArrayList<Value>();
		for(int i = 0; i < numParams; i++){
			myParams.add((Value) params.get(i));
		}
	}
	
	protected List<Value> getParams(){
		return myParams;
	}
	
	public abstract double evaluate();
	
	public int getNumParams(){
		return numParams;
	}
	protected void setNumParams(int n){
		numParams = n;
	}
	
	protected void setValue(double value){
		myValue.setValue(value);
	}
	public Value getValue(){
		return myValue; 
	}
	
	protected Environment getEnvironment() {
		return myEnvironment;
	}
	public void setEnvironment(Environment env) {
		myEnvironment = env;
	}

	public Controller getController(){
		return myController;
	}
	public void setController(Controller c){
		if(myController == null){
			myController = c;
		}
	}
	

}



