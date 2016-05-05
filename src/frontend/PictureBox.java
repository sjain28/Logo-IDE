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



public class PictureBox {
	private static final double PALETTE_BOX_HEIGHT = 60;
	private static final double PALETTE_BOX_WIDTH = 150;
	
	
		
	public VBox init(Controller myController){
		 
		ListView<String> listView =myController.getPictures();
				//super.getController().getShapes();
		 //magic
		URL resource = ClassLoader.getSystemClassLoader().getResource("resources/images");
		Collection<String> myFileList = new ArrayList<String>();
		File head = new File(resource.getPath());
		for(File fileEntry: head.listFiles()){
			myFileList.add(fileEntry.toURI().toString());
		}
		 
		 ObservableList<String> shapes = FXCollections.observableArrayList(myFileList);
		 VBox box = new VBox();
	     box.getChildren().addAll(listView);
	     VBox.setVgrow(listView, Priority.ALWAYS);

	     listView.setItems(shapes);
	     listView.setCellFactory( e-> handlePictureCellCreation());

		 box.setPrefWidth(PALETTE_BOX_WIDTH);
		 box.setPrefHeight(PALETTE_BOX_HEIGHT);
	     return box;
	}
	
	private ListCell<String> handlePictureCellCreation(){
		
		ListCell<String> myVal = new ImageCell();
		return myVal;

	}
}
