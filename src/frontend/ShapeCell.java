package frontend;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;

public class ShapeCell extends ListCell<String>{
	   
		private static final double SIDE = 10;
		private static final double OFFSET = 20;
		@Override
	     public void updateItem(String item,  boolean empty) {
       
			 super.updateItem(item, empty);
	         if (item != null) {
	        	 Shape shape = createShape(item);
	        	 shape.setTranslateX(OFFSET);
		         setText(String.valueOf((this.getIndex())));
	        	 setTextAlignment(TextAlignment.LEFT);
	        	 setGraphic(shape);
	        	 this.setGraphicTextGap(-OFFSET);
	         }
		}
		private Shape createShape(String sides){
			Shape myShape;
			
			switch(sides){
				case "0":
					Circle myCircle = new Circle();
					myCircle.setRadius(SIDE);
					myShape = myCircle;
					break;
				default:
					int numSides = Integer.parseInt(sides);
					Polygon myPoly = new Polygon();
					for(int i = 0; i< numSides; i++){
						myPoly.getPoints().add((Double) SIDE * Math.cos(2 * Math.PI * i / numSides));				
						myPoly.getPoints().add((Double) SIDE * Math.sin(2 * Math.PI * i / numSides));
					}
					myShape = myPoly;
			}
			return myShape;
		}
}
	 

