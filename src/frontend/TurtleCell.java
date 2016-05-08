package frontend;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import control.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import turtle.Agent;

public class TurtleCell extends ListCell<Agent>{
	   


	
		private static final double RECT_WIDTH = 30;
		private static final double OFFSET = 20;
	    private static final double RECT_HEIGHT = 30;
	    private ListView<String> myImageTrack;
	    private Controller myController;
	    private int selectedNumber;
		private ComboBox<String> turtlePick;
		private Stage myStage;
	    
		public TurtleCell(ListView<String> listView, Controller inController){
			myImageTrack = listView;
			myController = inController;
		}
	     public void updateItem(Agent item,  boolean empty) {
       
	    	 this.setOnMouseClicked(e -> handleClicked());
	         super.updateItem(item, empty);
	         ImageView myPic = new ImageView();
	         myPic.setFitHeight(RECT_HEIGHT);
	         myPic.setFitWidth(RECT_WIDTH);
	         myPic.setTranslateX(OFFSET);
	         if (item != null) {
	        	 
	        	 Image myImage = new Image(myImageTrack.getItems().get(item.getShape()));
	        	 myPic.setImage(myImage);
	        	 setText(String.valueOf((this.getIndex())));
	        	 setTextAlignment(TextAlignment.LEFT);
	        	 setGraphic(myPic);
	        	 this.setGraphicTextGap(-OFFSET);
	         }
		}
		private void handleClicked() {
			myStage = new Stage();
			VBox myVBox = new VBox();
			
			Scene myScene = new Scene(myVBox);
			buildTurtlePicker();
			myVBox.getChildren().add(turtlePick);
			myStage.setScene(myScene);
			myStage.show();

			
		}
		
		private void buildTurtlePicker() {
			// Appropriated from voogasalad
			turtlePick = new ComboBox<String>();
			
			Collection<String> numbers = new ArrayList<String>();
			int i = 0;
			while( i < myImageTrack.getItems().size()){
				numbers.add(Integer.toString(i, 10));
				i++;
			}
			
			turtlePick.getItems().addAll(numbers);
			
			turtlePick.setPromptText("Select Turtle Image");
			
			turtlePick.valueProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue ov, String t, String t1) {
					selectedNumber = Integer.parseInt(t1);
					myController.getTurtle().setShape(selectedNumber);
					myStage.close();
				}
			});
			
			

		}
		
}
	 

