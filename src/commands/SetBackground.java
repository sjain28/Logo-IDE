package commands;

import value.Value;

public class SetBackground extends Command{
	
	public SetBackground(){
		setNumParams(1);
	}
	@Override
	public double evaluate() {
		Value indexParam = (Value) getParams().get(0);
		int index = indexParam.getValue().intValue();
		getController().setBackGroundColor(getController().getColor(index));
		return index;
	}

}
