// This entire file is part of my masterpiece
// Hayden Bader

package frontend;

import control.Controller;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColorPickerCustom {
	private static final double COLOR_BOX_WIDTH = 90;
	private static final double COLOR_BOX_HEIGHT = 30;
	
	private static final int BOX_ROW = 2;
	private static final int LABEL_ROW = 0;
	
	public ColorPicker initColorPicker(Controller myController, int column, Label inLabel, Color myColor, GridPane grid){
		ColorPicker myColorPicker = new ColorPicker();
		myColorPicker.setPrefWidth(COLOR_BOX_WIDTH);
		myColorPicker.setPrefHeight(COLOR_BOX_HEIGHT);
		myColorPicker.setValue(myColor);
		addToGrid(grid, myColorPicker, inLabel, column, BOX_ROW, LABEL_ROW);
		return myColorPicker;
	}
	
	private void addToGrid(GridPane grid, Node toAdd, Node label, int column, int toAddRow, int labelRow){
		GridPane.setConstraints(toAdd, column, toAddRow);
		grid.getChildren().add(toAdd);
		GridPane.setConstraints(label,  column,  labelRow);
		grid.getChildren().add(label);
	}
}
