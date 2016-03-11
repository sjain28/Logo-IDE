package commands;

import control.Controller;
import parser.DoubleOptional;

public class SetPalette extends Command{
	
	public SetPalette(){
		setNumParams(4);
	}

	@Override
	public double evaluate() {
		DoubleOptional index = (DoubleOptional) getParams().get(0);
		DoubleOptional r =(DoubleOptional)  getParams().get(1);
		DoubleOptional g =(DoubleOptional)  getParams().get(2);
		DoubleOptional b = (DoubleOptional) getParams().get(3);
				
		getController().setPallette(index.getValue().intValue(), r.getValue().intValue(), g.getValue().intValue(), b.getValue().intValue()); 
		setValue(index.getValue());
		return index.getValue();
	}

}
