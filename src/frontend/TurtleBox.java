// This entire file is part of my masterpiece
// Hayden Bader

package frontend;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import turtle.Agent;



public class TurtleBox {
	private static final double PALETTE_BOX_HEIGHT = 60;
	private static final double PALETTE_BOX_WIDTH = 150;
	private Controller thisController;
	
		
	public VBox init(Controller myController){
		 thisController = myController;
		ListView<Agent> listView =myController.getTurtleAgents();
				//super.getController().getShapes();
		 //magic
		URL resource = ClassLoader.getSystemClassLoader().getResource("resources/images");
		Collection<Agent> myTurtleTypes = new ArrayList<Agent>();

		
		 
		 ObservableList<Agent> turtles = FXCollections.observableArrayList(myTurtleTypes);
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(turtles);
	     listView.setCellFactory( e-> handlePictureCellCreation());

		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}
	
	private ListCell<Agent> handlePictureCellCreation(){
		
		
		ListCell<Agent> myVal = new TurtleCell(thisController.getPictures(),thisController);
		return myVal;

	}
}
