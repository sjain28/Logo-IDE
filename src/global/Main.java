package global;

import frontend.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Robert C. Duvall
 */
public class Main extends Application 	
{
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static Window display;

    @Override
    public void start (Stage s) 
    {
    	//make sure to initialise backend too
        display = new TextBox(500, 500);
        s.setTitle(display.getTitle());
        Scene mainScene = display.init();
        s.setScene(mainScene);
        s.show();
        
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		                e -> display.step(SECOND_DELAY));
		                
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
        
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
