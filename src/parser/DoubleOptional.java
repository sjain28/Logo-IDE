package parser;

public class DoubleOptional{
	private Double myValue;
	
	public DoubleOptional() {
		myValue = null;
	}
	
	public DoubleOptional(double d) {
		myValue = d;
	}
	
	public Double getValue() {
//		if (myValue == null) {
//			throw new Exception();
//			//throw new ValueNotInitializedException();
//		}
		return myValue;
	}
	
	public void setValue(double d){
		myValue = d;
	}
}
