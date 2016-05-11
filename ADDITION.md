###Estimation: before looking at the old code:
* How long do you think it will take you to complete this new feature?

I estimate that this addition will take around 30 minutes to complete since the existing framework should be able to support these additions due to the way we made the commands very open to extension.
* How many files will you need to add or update? Why?
I would estimate there are around 4 files to update. We need to add a new class for the new command and modify the file the resource bundle looks at the interpret the command. In addition, because these are front end modifications, there might need to be modifications to the front end to account for the new capabilities we want from them since we hadn't expected to need the front end to support it.
###Review: after completing the feature:
* How long did it take you to complete this new feature?

It took roughly the amount of time estimated, 30 minutes, to complete.
* How many files did you need to add or update? Why?

I had to modify 5 files since unlike what I had estimated, there were two command classes that needed to be added to support having different border behaviors - a class for the new command and a command class to allow the user to use the project's current default border behavior.
* Did you get it completely right on the first try?

I was able to get it on the first try.
###Analysis: what do you feel this exercise reveals about your project's design and documentation?
* Was it as good (or bad) as you remembered?

The ease of being able to add the new command reveals the strength of our design in allowing for easy extension of commands, even ones we didn't anticipate.
* What could be improved?

While trying to implement to new feature, I saw how the communication between front end and back end could end up being difficult at some points because there was no way to change certain features of the front end from the controller currently in place, such as window size. The front end would have to pull information from the controller than the backend updates in order to communicate between front end and backend. This could be improved by having

* What would it have been like if you were not familiar with the code at all?

I think it would have been vastly more difficult to try and do this without being familiar with the code because you had to know the structure and flow of the code in order to know which parts of it to change and which parts to leave the same. There would have to be documentation in place explaining this flow of the program to make it easy to understand by someone who hadn't worked on it before.
