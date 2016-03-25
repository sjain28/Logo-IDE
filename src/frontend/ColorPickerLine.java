// This entire file is part of my masterpiece
// Hayden Bader

package frontend;

import java.util.Collection;

import control.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import turtle.Agent;

public class ColorPickerLine extends ColorPickerCustom {

	ColorPicker myColorPicker;
	
	public ColorPicker initColorPicker(Controller myController, int column, Label inLabel, Color myColor, GridPane grid){
		myColorPicker = super.initColorPicker(myController, column, inLabel, myColor, grid);
		myColorPicker.setOnAction(e -> changeLineColor(myController));
		return myColorPicker;
	}
	
	private void changeLineColor(Controller myController) {
		Collection<Agent> myActives = myController.getActiveTurtles();
		for(Agent myTurtle: myActives ){
			myTurtle.setPenColor(myColorPicker.getValue());
		}

		return;
	}
	
}
