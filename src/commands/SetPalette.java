package commands;

import control.Controller;
import parser.DoubleOptional;

public class SetPalette extends DisplayCommand{
	
	public SetPalette(){
		setNumParams(4);
	}
	
	@Override
	public double doCommand(Controller c) {
		DoubleOptional index = (DoubleOptional) getParams().get(0);
		DoubleOptional r =(DoubleOptional)  getParams().get(1);
		DoubleOptional g =(DoubleOptional)  getParams().get(2);
		DoubleOptional b = (DoubleOptional) getParams().get(3);
				
		c.setPallette(index.getValue().intValue(), r.getValue().intValue(), g.getValue().intValue(), b.getValue().intValue()); 
		setValue(index.getValue());
		return index.getValue();
	}

}
