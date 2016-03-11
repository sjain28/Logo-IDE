package commands;

import value.Value;

public class SetPalette extends Command{
	
	public SetPalette(){
		setNumParams(4);
	}

	@Override
	public double evaluate() {
		Value index = (Value) getParams().get(0);
		Value r =(Value)  getParams().get(1);
		Value g =(Value)  getParams().get(2);
		Value b = (Value) getParams().get(3);
				
		getController().setPallette(index.getValue().intValue(), r.getValue().intValue(), g.getValue().intValue(), b.getValue().intValue()); 
		setValue(index.getValue());
		return index.getValue();
	}

}
