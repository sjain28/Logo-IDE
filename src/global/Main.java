package global;

import control.Controller;
import frontend.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Robert C. Duvall
 */
public class Main extends Application 	
{
    public static final int FRAMES_PER_SECOND = 60;
    

    @Override
    public void start (Stage s) 
    {
 		Controller myBackEnd =  new Controller();
		GUI myFrontEnd = new GUI(FRAMES_PER_SECOND, myBackEnd);						
		Scene scene = myFrontEnd.init();
		s.setScene(scene);
		s.show();		
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
