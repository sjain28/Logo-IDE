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

    private static final double TEXTBOX_WIDTH = 500;
    private static final double TEXTBOX_HEIGHT = 500; 
    private static final double PASTCOMMANDS_WIDTH = 200;
    private static final double PASTCOMMANDS_HEIGHT = 200;
    private static final double DISPLAY_WIDTH = 400;
    private static final double DISPLAY_HEIGHT = 400;
    private static final double MAIN_WIDTH = 1200;
    private static final double MAIN_HEIGHT = 700;
	private static final double VARIABLESTATES_HEIGHT = 200;
	private static final double VARIABLESTATES_WIDTH = 200;
	private static final int LINE_SPACING = 50;
    
    private Controller myController;
    private BorderPane mainPane = new BorderPane();
    
	public GUI(int FPS, Controller inController){
		FRAMES_PER_SECOND = FPS;
		myController = inController;
	}
	
	
	public Scene init(){
		
	   	System.setProperty("glass.accessible.force", "false");
	
	   	TextBox textBox = initTextbox();
	    initPastCommands(textBox);
	    VariableStates variableStates = initVariableStates();

	    
	    Scene scene = new Scene(mainPane, MAIN_WIDTH, MAIN_HEIGHT);
	    Display display = initDisplay();
	    mainPane.setCenter(display.getRoot());
	    
	    initControlPanel(display);
	    
	    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				(e -> handleEvent(display, variableStates)));
		
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
		return scene;
	}
	
	private void handleEvent(Display display, VariableStates variableStates){
		display.step(SECOND_DELAY);
		variableStates.step(SECOND_DELAY);

	}
	
	private TextBox initTextbox(){
	    TextBox textBox = new TextBox(TEXTBOX_WIDTH,TEXTBOX_HEIGHT);
	    textBox.setController(myController);
	    textBox.init();
	    mainPane.setBottom(textBox.getRoot());
	    BorderPane.setAlignment(textBox.getRoot(), Pos.CENTER);
	    return textBox;
	}
	
	private void initPastCommands(TextBox textBox){
	    PastCommands pastCommands = new PastCommands(PASTCOMMANDS_WIDTH, PASTCOMMANDS_HEIGHT);
	    pastCommands.init();
	    mainPane.setRight(pastCommands.getRoot());
	    textBox.setPastCommandBox(pastCommands);
	    
	}
	
	private VariableStates initVariableStates(){
	    VariableStates variableStates = new VariableStates(VARIABLESTATES_WIDTH, VARIABLESTATES_HEIGHT);
	    variableStates.init();
	    variableStates.setController(myController);
	    mainPane.setLeft(variableStates.getRoot());
	    return variableStates;
	}
	
	private Display initDisplay(){
	    Display display = new Display(DISPLAY_WIDTH,DISPLAY_HEIGHT, LINE_SPACING);
	    display.setController(myController);
	    display.init();
	    return display;
	}
	
	private void initControlPanel(Display display){
	    ControlPanel myControlPanel = new ControlPanel(display);
	    myControlPanel.setController(myController);
	    myControlPanel.init();
	    mainPane.setTop(myControlPanel.getRoot());
	}
}
