// This entire file is part of my masterpiece
// Hayden Bader

package frontend;

import control.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColorPickerBackground extends ColorPickerCustom{
	
	ColorPicker myColorPicker;
	
	public ColorPicker initColorPicker(Controller myController, int column, Label inLabel, Color myColor, GridPane grid){
		myColorPicker = super.initColorPicker(myController, column, inLabel, myColor, grid);
		myColorPicker.setOnAction(e -> changeBackgroundColor(myController));
		return myColorPicker;
	}
	
	private void changeBackgroundColor(Controller myController) {
		myController.setBackGroundColor(myColorPicker.getValue());
		return;
	}
}
