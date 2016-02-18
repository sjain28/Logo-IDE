### SLogo Architecture Design
######When are errors detected and how are they reported?
> The backend methods would detect the error and throw an exception. The front end methods then catch the exceptions and display some kind of error message to the user.
######What do commands know, when do they know it, and how do they get it?
> Commands should know the number/type of arguments. They should know it before the program starts. Commands would be considered objects and get their arguments through their constructors.
######How is the GUI updated after a command has completed execution?
> The user would press a button or the program would detect when the user hits “enter” and call a method that updates the GUI. The GUI would receive a list of updates to draw and could store the list of commands in the history.

###CRC Cards
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


###APIs
####Internal Frontend
* List<Command> Parser.getHistory()
* void Setup.updateHistory(List<Command>)
* void Setup.init() 

####External Frontend
* void Display.updateDisplay() //Updates display with turtle state
* void GUI.runCommands(List<String>, Parser) //GUI passes to parser
* 
####Internal Backend
* ? Command.evaluate() // return result of executing command (ex. an instruction for turtle)
* Command CommandFactory.getCommand(?) //Returns a command based on input from Parser

####External Backend
* List<Point2D[]> Turtle.getNewPoints() //Returns Turtle’s upcoming points
* void Turtle.addCommands(List<Command>...) //Adds a list of commands to Turtle

