#API Review
--------------------------------
Patrick Grady - plg5
Bobby Wang - bcw22

###Part 1
1. The separation between the front and back end in the UML is very clear. The binding has a single function that the front end calls, and the back end returns one data type. This design was chosen to heavily emphasize flexibility, and it enables the back end to be entirely independent of implementation-specific details. Another front end could be plugged in to work with minimal effort. Additionally, the generic way which the back end describes states would easily allow extension if required.

2. The package-protected paradigm permeating the back end and front end prohibits information flow except for very specific API calls

3. The program will use an exception based architecture to handle errors. This permits the easy upward flow of information, and automatically handles the termination of the current command. This will handle parsing errors and logical errors thrown by the back end.
4. The API is well designed since it limits the binding to be small so that there are no unneeded external functions or data. This allows our contract between the front and back to be very rigid and consistent, and ultimately extendable if needed.

### Part 2

#### Demo what you currently have running (it could be anything, but it should definitely be something).

Completed.

#### Come up with at least four use cases for your part (it is absolutely fine if they are useful for both teams).

1. Update the state of visual state of board when a new command is inputted

2. Display an error when an invalid command is inputted

3. Change the language that the slogo commands are entered and parsed in

4. Change the pen color of a turtle

5. Change the background color of the image

#### What feature/design problem are you most excited to work on?

##### Patrick

I'm most excited to work on making Slogo work in multiple languages. There's a lot of extensibility involved with this language feature, and there's so many ways of implementing it. I hope that I'll be able to find a relatively intuitive, clean way of implementing this feature.

##### Bobby
I'm most excited to work on updating the visual state based solely on the command module data. I've never worked on front end before (outside of my game), so this experience will teach me a lot. It also allows for a lot of personal creativity, since I'm almost solely in charge of the visual look of our UI.

##### What feature/design problem are you most worried about working on?

##### Patrick
I'm most worried about displaying the turtle in an intuitive, clean way. With one turtle, the issues shouldn't be too bad. However, with multiple turtles and pen colors, the canvas can quickly get messy. When we incorporate other issues like re-sizing the canvas if the turtle moves off screen, there are a whole host of other extensiblity issues that can crop up.

##### Bobby
I'm worried about displaying the current state of variables and the list of past entered commands. Displaying them in and of themsleves shouldn't be too bad - however, when it comes to re-executing commands by clicking on them, there are many issues of encapsulation that may come up.
