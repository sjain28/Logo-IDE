package global;

import control.Controller;
import frontend.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 	
{
    public static final int FRAMES_PER_SECOND = 30;
    

    @Override
    public void start (Stage s) 
    {
    	Controller myBackEnd =  new Controller();
		GUI myFrontEnd = new GUI(FRAMES_PER_SECOND, myBackEnd);		
		myBackEnd.setGUI(myFrontEnd);
		Scene scene = myFrontEnd.init();
		s.setScene(scene);
		s.show();		
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
