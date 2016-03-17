package frontend;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class ImageCell extends ListCell<String>{
	   
		private static final double RECT_WIDTH = 30;
		private static final double OFFSET = 20;
	    private static final double RECT_HEIGHT = 30;
		@Override
	     public void updateItem(String item,  boolean empty) {
       
	         super.updateItem(item, empty);
	         ImageView myPic = new ImageView();
	         myPic.setFitHeight(RECT_HEIGHT);
	         myPic.setFitWidth(RECT_WIDTH);
	         myPic.setTranslateX(OFFSET);
	         if (item != null) {
	        	 Image myImage = new Image(item);
	        	 myPic.setImage(myImage);
	        	 setText(String.valueOf((this.getIndex())));
	        	 setTextAlignment(TextAlignment.LEFT);
	        	 setGraphic(myPic);
	        	 this.setGraphicTextGap(-OFFSET);
	         }
		}
		
}
	 

