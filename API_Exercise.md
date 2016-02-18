API Exercise
=========

Team 17: Hayden Bader, Austin Hua, Saumya Jain, Bobby Wang

### CellSociety API Critique

##### Simulation

###### External

There are several methods which are in the external simulation API. The `getState` method is necessary for drawing an accurate UI. The `getNumCells` method is necessary for effective visualization of the percentages of each cell, and the `getX` and `getY` methods are necessary for both writing to XMLs and displaying cells in the visualization. Even methods which are primarily used within the simulation module such as `isToroidal` tend to have one or two functions outside of the module. In the case of `isToroidal`, the method is needed in one case - when writing the state to an external XML file in the configuration module.

###### Internal

Having said that, there are several methods which are only necessary for internal use. Most of these fall into the class of methods that the `Board` and `Cell` classes (and subclasses) use to communicate with one another. For example, `setState` and `getCurrentState` in the cell are only needed to communicate with the board. The board then uses external API's such as `setStates` and `setSingleState` in order to interface with other modules like the UI.

##### Configuration

###### External

There are many methods in the external configuration API. The `getBasicInfo`, `getGlobalInfo`, `getCells`, and other methods which read information from the XML pass that information to both the back end and the visualization modules, which use that information to style the simulation, set initial parameters, and set up the board's initial state. On the flip side, the various `writeInfo` methods such as `writeCurrentBoardState` and `writeGlobal` work with the back end and visualization modules in order to output an XML file which represents the simulation state.


###### Internal

There are no methods on the configuration internal API because the sole purpose of the configuration module is to interface with the simulation module (which contains the actual data) and the visualization module (which the users can see). There is little need for the configuration module to have methods which act solely for itself, since any data that is stored only within the configuration module (as opposed to written to an external file or shared with another module) is essentially lost.


##### Visualization

###### External

There is a lot of interaction between the visualization module and the other modules. the `updateBoard` and `changeSingleCell` methods need to interact with the back end to get the new board state information. Methods for counting the number of types of each cell similarly are part of the external API. `readXML` and `writeXML` are also external, as they interact with the configuration module.

###### Internal

There are also several methods which are only available to classes within the visualization module. For example, the methods for initializing the game, launching the application, and setting a global UI are all in the internal API as `initialize`, `setUI`, and `start`.


### SLogo Architecture Design
###### When are errors detected and how are they reported?
> The backend methods would detect the error and throw an exception. The front end methods then catch the exceptions and display some kind of error message to the user.
###### What do commands know, when do they know it, and how do they get it?
> Commands should know the number/type of arguments. They should know it before the program starts. Commands would be considered objects and get their arguments through their constructors.
###### How is the GUI updated after a command has completed execution?
> The user would press a button or the program would detect when the user hits “enter” and call a method that updates the GUI. The GUI would receive a list of updates to draw and could store the list of commands in the history.

### CRC Cards
Class: Parser  
Responsibilities: Reads user input and produces a list of commands to execute.  
Collaborators: Instantiated by the front end to parse user input. Sends output to the backend to be processed.  

Class: Command  
Responsibilities: Receives parsed version of Logo commands. Translates commands into instructions. Passes instructions to Turtle.   
Collaborators: Instantiated by CommandFactory in Parser, passed to Turtle where it is interpreted.  

Class: CommandFactory  
Responsibilities: Makes new commands based on the input read in from the Parser  
Collaborators: Receives information from the Parser and creates and returns new Command objects based on information.  

Class: Setup  
Responsibilities: Creates and attaches a Grid object and a GUI class. Instantiates a Parser to pass user input to, instantiates Turtle(s), and gives them commands.  
Collaborators: Instantiates GUI and Turtle objects  

Class: Display  
Responsibilities: Visually displays the pen strokes that represent the turtle’s movement.
Collaborators: Has an updateDisplay method that pulls drawing commands from turtle. Attached to Setup(?)  

Class: GUI  
Responsibilities: Contains buttons and text fields that are used to customize the display and input/execute commands.  
Collaborators: Passes commands to Parser, modifies appearance of Grid.  

Class: Turtle  
Responsibilities: Holds and updates location/orientation of the turtle and states/attributes of the turtle(pen) (on/off, color, width) IN THE BACKEND. Sends drawing commands to the front end.
Collaborators: Receives input from Parser, sends can be called by Grid to return a list of drawing commands to Display  


### APIs
#### Internal Frontend
* List<Command> Parser.getHistory()
* void Setup.updateHistory(List<Command>)
* void Setup.init()

#### External Frontend
* void Display.updateDisplay() //Updates display with turtle state
* void GUI.runCommands(List<String>, Parser) //GUI passes to parser


#### Internal Backend
* Command.evaluate() // return result of executing command (ex. an instruction for turtle)
* Command CommandFactory.getCommand(?) //Returns a command based on input from Parser

#### External Backend
* List<Point2D[]> Turtle.getNewPoints() //Returns Turtle’s upcoming points
* void Turtle.addCommands(List<Command>...) //Adds a list of commands to Turtle
