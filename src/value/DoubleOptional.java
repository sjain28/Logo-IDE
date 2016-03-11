package value;

public abstract class DoubleOptional{
	private Double myValue;
	
	public DoubleOptional() {
		myValue = null;
	}
	
	public Double getValue() {
		return myValue;
	}
	
	public void setValue(double d){
		myValue = d;
	}
}
