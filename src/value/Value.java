package value;

public abstract class Value{
	private Double myValue;
	
	public Value() {
		myValue = null;
	}
	
	public Double getValue() {
		return myValue;
	}
	
	public void setValue(Double d){
		myValue = d;
	}
}
