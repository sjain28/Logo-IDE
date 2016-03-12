package frontend;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class ImageCell extends ListCell<String>{
	   
		private double RECT_WIDTH = 100;
		private double OFFSET = 20;
	    private double RECT_HEIGHT = 20;
		@Override
	     public void updateItem(String item,  boolean empty) {
       
	         super.updateItem(item, empty);
	         ImageView myPic = new ImageView();
	         myPic.setFitHeight(RECT_HEIGHT);
	         myPic.setFitWidth(RECT_HEIGHT);
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
	 

