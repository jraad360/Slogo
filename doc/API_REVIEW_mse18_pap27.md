# Part 1
1. What about your API/design is intended to be flexible?
The backend is flexible in adding new functions. The front end is intended to be more flexible in adding new turtles and the pens that come with them.
2. How is your API/design encapsulating your implementation decisions?
Public methods in API communicate information but not implementation, i.e. showError takes in a String, it does not try and catch.
3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Possible file errors with the turtle sprite. This can be handled by offering a pop-up error message with the option for the user to type in the correct file. 
4. Why do you think your API/design is good (also define what your measure of good is)?
I like the separation between the front and backend. The front end does not need to know the implementation decisions of the backend at all in order to succeed.

# Part 2
1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The front and backend implement encapsulation through inter-class access purely in public API methods. We limit the number of API methods to ensure strong encapsulation. The specific code will work by creating classes in an object oriented nature, with each class having its own behavior.
2. What feature/design problem are you most excited to work on?
I am excited to learn how to do frontend! I have not touched it before.
3. What feature/design problem are you most worried about working on?
Also learning the frontend, because I have no idea what I am doing.
4. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * User enters "fd 50":
        * Scene.getUserInput(fd 50) - for backend and history
            * Parser.parseToExecute()
            * ErrorChecking.checkError() if needed. Then showError() within if needed.
            * Parser.parseToExecute() calls Function.execute() and Function.runFunctions()
            * within each execute():
                * Scene.getPosition()
                * Turtle.updatePosition()
                * Turtle.getOrientation()