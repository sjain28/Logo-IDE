package frontend_helper;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BackgroundColorPane extends CustomColorPane {
	
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final int BOX_ROW = 2;
	private static final int LABEL_ROW = 0;
	private static final String BACK_STRING = "Select a Background";
	
	private ColorPicker myColorPicker = new ColorPicker();
	
	public BackgroundColorPane(GridPane grid, double width, double height, int x_location){
		myColorPicker.setPrefWidth(width);
		myColorPicker.setPrefHeight(height);
		myColorPicker.setValue(DEFAULT_COLOR);
		addToGrid(grid, myColorPicker, new Label(BACK_STRING), x_location, BOX_ROW, LABEL_ROW);
	}
	
	private void addToGrid(GridPane grid, Node toAdd, Node label, int column, int toAddRow, int labelRow){
		GridPane.setConstraints(toAdd, column, toAddRow);
		grid.getChildren().add(toAdd);
		GridPane.setConstraints(label,  column,  labelRow);
		grid.getChildren().add(label);
	}
	
	public ColorPicker getColorPicker(){
		return myColorPicker;
	}
	
}
