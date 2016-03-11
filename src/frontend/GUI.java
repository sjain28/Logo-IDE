package frontend;

import control.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class GUI {
	
    private static int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Controller myController;
    
	public GUI(int FPS, Controller inController){
		FRAMES_PER_SECOND = FPS;
		myController = inController;
	}
	
	
	public Scene init(){
			
		
	   	System.setProperty("glass.accessible.force", "false");
	
		//make sure to initialise backend too
		
		//myController = new Controller();
		
	    BorderPane mainPane = new BorderPane();
	    TextBox textBox = new TextBox(500,500);
	    textBox.setController(myController);
	    textBox.init();
	    mainPane.setBottom(textBox.getRoot());
	    BorderPane.setAlignment(textBox.getRoot(), Pos.CENTER);
	    
	    
	    PastCommands pastCommands = new PastCommands(200,200);
	    pastCommands.init();
	    mainPane.setRight(pastCommands.getRoot());
	    textBox.setPastCommandBox(pastCommands);
	    
	    
	    VariableStates variableStates = new VariableStates(200,200);
	    variableStates.init();
	    variableStates.setController(myController);
	    mainPane.setLeft(variableStates.getRoot());
	    
	    Scene scene = new Scene(mainPane, 1200, 700);
	    Display display = new Display(400,400, 50);
	    display.setController(myController);
//	    display.getController().setActiveTurtle(display.getTurtle());
	    display.init();
	    
	    ControlPanel myControlPanel = new ControlPanel(scene, display);
	    myControlPanel.setController(myController);
	    myControlPanel.init();
	    mainPane.setTop(myControlPanel.getRoot());
	    
	    
	    mainPane.setCenter(display.getRoot());
	    
	    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						display.step(SECOND_DELAY);
//						variableStates.step(SECOND_DELAY);
					}
				});
		
		
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
		return scene;
	}
}
