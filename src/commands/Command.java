package commands;

import java.util.ArrayList;
import java.util.List;

import control.Controller;
import frontend.DialogHandler;
import parser.Environment;
import parser.Parser;
import value.Value;
import value.NumericalValue;

public abstract class Command {
	private Value myValue = new NumericalValue(); 
	private int numParams;
	private List<Object> myParams = new ArrayList<>();
	private Environment myEnvironment;
	private Controller myController; //Refactoring: Have some kind of frontend interface that gets exposed to backend instead of the whoel controller
	
	public void setParams(List<Object> params) throws Exception{
		if(params.size() != getNumParams()){
			DialogHandler eh = new DialogHandler(50, 50);
			eh.init();
			eh.openPopup("NumParamsException");	
		}
		try{
			initParams(params);
		}
		catch(Exception e){
			DialogHandler eh = new DialogHandler(50, 50);
			eh.init();
			eh.openPopup("InvalidInputException");
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
		
	public void setEnvironment(Environment env) {
		myEnvironment = env;
	}
	protected Environment getEnvironment() {
		return myEnvironment;
	}
	
	public void setController(Controller c){
		if(myController == null){
			myController = c;
		}
	}
	
	public Controller getController(){
		return myController;
	}
}



