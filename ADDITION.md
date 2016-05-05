###Estimation
* I expect this addition to take 25 minutes. Our model of commands on the backend was very straightforward, so I will only have to wrote a couple
of methods. I estimate it'll take 10 minutes to write the actual code, and 15 to refresh myself with where to find things. 
* I expect to update 2 files; I will create one new class for the Fence command, and will modify the properties file 
with all of the command names

###Review
* It took about 20 minutes. 
* I had to modify 3 files; The two listed above, and I had to modify one file to be able to access a constant for the size of the window.
* Once I worked out the math for calculating when to stop at the edge, yes, I was able to implement it on the first try.

###Analysis
* This exercise confirmed my earlier assessment of our backend, which is that the commands package does a very good job of abstracting away 
most of the interaction with the rest of the backend, so that commands only have to take care of their direct responsibility. 
* This was one of the commands for which I had to interface with the frontend in order to access the window size. Right now sharing of 
information from backend to frontend is well implemented, but the reverse is really not. The only channels for access to frontend
information are messy ones, like getters that shouldn't exist or public static final variables. A cleaner way to do this would have been
more extensive use of properties files, or some kind of dedicated intermediary in addition to the controller.
* After reading documentation about the Command and TurtleCommand superclasses and looking at a couple of Commands, it would have been
not too difficult to code the logic and apply it to a Turtle. The tough part would have been accessing the relevant frontend info. To
a newcomer, this would be next to impossible, for reasons explained above. 
