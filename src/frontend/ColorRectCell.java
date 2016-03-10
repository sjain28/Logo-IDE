package frontend;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class ColorRectCell extends ListCell<String>{
	   
		private double RECT_WIDTH = 100;
		private double OFFSET = 20;
	    private double RECT_HEIGHT = 20;
		@Override
	     public void updateItem(String item,  boolean empty) {
       
	         super.updateItem(item, empty);
	         Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
	         rect.setTranslateX(OFFSET);
	         if (item != null) {
	        	 String newItem = "rgb(" + item + ")";
	        	 rect.setFill(Color.web(newItem));
	        	 setText(String.valueOf((this.getIndex())));
	        	 setTextAlignment(TextAlignment.LEFT);
	        	 setGraphic(rect);
	        	 this.setGraphicTextGap(-RECT_WIDTH);
	         }
		}
}
	 

