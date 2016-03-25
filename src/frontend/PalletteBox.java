// This entire file is part of my masterpiece
// Hayden Bader

package frontend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PalletteBox {
	
	private static final double PALETTE_BOX_HEIGHT = 60;
	private static final double PALETTE_BOX_WIDTH = 150;
	private static final String COLOR_RESOURCE_PACKAGE = "resources.frontend/colors";

	public VBox initPaletteBox(Controller myController){
		
		 ListView<String> listView = myController.getPalette();
		 ResourceBundle myColors = ResourceBundle.getBundle(COLOR_RESOURCE_PACKAGE);
		 
		 Collection<String> myInColors = new ArrayList<>();
		 for( String key: myColors.keySet()){
			 myInColors.add(myColors.getString(key));
		 }
		 ObservableList<String> indices = FXCollections.observableArrayList(myInColors);
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);
	     listView.setItems(indices);
	     listView.setCellFactory( e-> handleColorCellCreation());
		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}
	
	private ListCell<String> handleColorCellCreation(){
		
		ListCell<String> myVal = new ColorRectCell();
		return myVal;

	}
	
}
