package global;

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
    
    private static Stage help;
    

    @Override
    public void start (Stage s) 
    {
    	//make sure to initialise backend too
    	
        BorderPane mainPane = new BorderPane();
        TextBox textBox = new TextBox(500,500);
        textBox.init();
        mainPane.setBottom(textBox.getRoot());
        BorderPane.setAlignment(textBox.getRoot(), Pos.CENTER);
        
        PastCommands pastCommands = new PastCommands(200,200);
        pastCommands.init();
        mainPane.setRight(pastCommands.getRoot());
        textBox.setPastCommandBox(pastCommands);
        
        Scene scene = new Scene(mainPane, 1200, 700);
        Display display = new Display(400,400, 50);
        ControlPanel myControlPanel = new ControlPanel(scene, display);
        myControlPanel.init();
        mainPane.setTop(myControlPanel.getRoot());
        mainPane.setCenter(display.getCanvas());
        
        
        s.setScene(scene);
        s.show();
        
        
        //technically this works
        getHostServices().showDocument("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php");
        
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
