SLogo Addition
=================

## Estimation: before looking at the old code:

##### How long do you think it will take you to complete this new feature?
I believe it will take less than 2 hours maximum.

##### How many files will you need to add or update? Why?
I will need to add two new command classes to the commands package, because each command is its own class. In addition, I will need to update the `English.properties` file so that `SCALE` and `WINDOW` are valid commands. Finally, I will need to update the `Display` class so that it reflects the new scaling logic. Overall, this results in 4 classes added/updated.

## Review: after completing the feature:


##### How long did it take you to complete this new feature?
It took around 2 hours.


##### How many files did you need to add or update? Why?
In addition to the 4 files mentioned above, I also needed to update the `Controller`, `Main`, `Parser`, `CommandFactory` and `GUI` classes. I needed to make these changes because our old design wasn't as extensible as I had thought. We had flipped the order of reasoning of having a back end Model being the basis for front end View, and instead had a view containing a model. This meant I had to add a few getter methods in order to call the methods I wanted to in the front end. If I had more time, I would completely refactor our project into what I now believe is good design. However, with my limited time, I made it so that these two new methods are called using reflection and through the observed `CommandFactory` notifying the observing `Controller`.

##### Did you get it completely right on the first try?
I did not get it completely right on the first try. It was pretty easy to add a new command and make it change our model, but getting the logic of the View correct took a long time. We had scaling logic built in, but it was split up into three different methods: scaling grid lines, scaling ImageView sizes, and scaling previous turtle points. I had to change all three of these methods after some degree of trial and error in order to get a working implementation of this feature.

## Analysis: what do you feel this exercise reveals about your project's design and documentation?


##### Was it as good (or bad) as you remembered?
Our design was fairly solid, but it wasn't as solid as I had remembered. I was so entrenched in the VoogaSalad architecture that it was a bit difficult switching gears and remembering exactly how I wrote SLogo. After digging around for about 30 minutes, the overall structure of the project became clearer, though. Our use of properties files to link commands and text inputs was very useful in terms of being able to easily add new commands or change which inputs led to which commands being generated.



##### What could be improved?
We didn't use reflection or the observable-observer pattern nearly as much as we should have. We were updating turtle positions every keyframe, which was a waste as most turtles weren't moving on any given frame. In addition, we had a front and back end separation, but our architecture could've been made more intuitive by sticking to standard M-V-C.


##### What would it have been like if you were not familiar with the code at all?

It would've likely taken at least an hour longer to familiarize myself with the project before I would be able to even start coding. Even though our macro architecture wasn't as clear as it could have been, within each class our code was fairly clean.
