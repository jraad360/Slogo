# Part 1

1. What about your API/design is intended to be flexible?

Adding commands should definitely be flexible, and it's probably important to
keep track of multiple turtles. Anything dealing with the environment history.
Also, creating new functions should be flexible so that you can have a new
function do anything, including using old created functions as well as any kind
of existing function. 

2. How is your API/design encapsulating your implementation decisions?

My design has TurtleSprite and Pen objects, which encapsulate those specific
movements, i.e. drawing and moving. The part of the View that deals with the
command log and command line are completely separate from any other parts of
the View. They log and the line never directly touch anything else, only via 
the back end. 

3. What exceptions (error cases) might occur in your part and how will you 
handle them (or not, by throwing)?

It could be possible to obtain an exception for uploading an incompatible
file for the TurtleSprite's ImageView. The best way to get around it would be
to display a popup window telling the user to give a compatible file. There
should also be a method for presenting an error thrown from the back end.

4. Why do you think your API/design is good (also define what your measure of 
good is)?

One good thing is that the major aspects of the View (CommandDisplay, 
ScreenDisplay, TurtleSprite, Pen) are all very encapsulated. Everything can
stand alone, and anything with behavior has its own objected. In this way, the
design of the View is very object-oriented. Furthermore, the CommandLog object
only really needs to communicate with the CommandLine, which reduces the
possibility of errors if we were to, say, read a file.

# Part 2

1. How do you think Design Patterns are currently represented in the design or 
could be used to help improve the design?

The CommandDisplay, ScreenDisplay, and TurtleSprite objects are all pretty
encapsulated and separated from each other. On the front end, it's interesting
to see that these different parts don't necessarily communicate a lot with each
other, and instead each of these has some way of communicating with the back
end. Keeping everything in separate, specific classes is good for flexibility 
and for usability. Having every aspect of the front end be capable of doing
its own thing without any help is good.

2. What feature/design problem are you most excited to work on?

I'm excited to animate the turtles and write out what happens once they're told
to perform an action, and to see it happen.

3. What feature/design problem are you most worried about working on?

I'm nervous about making commands for screen display.

4. Come up with at least five use cases for your part (it is absolutely fine if
they are useful for both teams).

a. -- User enters "fd 50" --
    CommandLine.getText() // gets input from command line
    View.addToCommandLog() // adds that line into command log
    CommandParser.parseCommand() // is called from command line
    TurtleController.setLocation(), .setHeading() // all of this in back end
    TurtleSprite.move() // called from the back end and executes in front end