# Slogo
## Basic Details
* Names: Mary Stuart Elder, Jamie Palka, Jorge Raad, Eric Werbel, Deniz Simsek
* Date started: February 18
* Date finished: March 8
* Total hours spent on project: ~70 each
## Individual Roles
### Mary Stuart
Worked primarily on the internal frontend. In particular, made the Bar ScrollPaneBar, and Display superclasses. From there, focused energy on the EnvironmentInfoBar, TurtleArea/InfoBar, TabController, and aspects of SlogoView. Assisted in debugging when possible. 
### Jamie
Worked on the internal backend. Implemented the complex subclasses Command, the InputType interface, and a lot of the classes which implement the InputType interface, the most complex of which is the BracketList class. Implemented the logic for the CommandRunner and the Parser.
### Jorge
Worked on the internal and external backend. Implemented a lot of the subclasses of Command, the most complicated of which were MakeUserInstruction and UserCommand. Enabled the backend to run commands on multiple turtles as well as run Tell, Ask, and AskWith. Also created the PropertyFileWriter and implemented the communication from backend to frontend through the list of maps.
### Eric
Worked on external frontend including the Turtle, Pen, and ErrorHandler classes. Also worked on the NavBar and CommandLine classes. Did a lot of code restructuring and splitting things into different classes to make the code easier to read and understand. Also took care of how the Turtle would update after commands were run and determining whether a Turtle is active or not. 
### Deniz
Worked on internal backend and external backend. Implemented several turtle commands, external backend package and the communication of turtle data between frontend and backend. Focused on BackendController, Command classes and ExternalBackend interface.

## Resources Used
* Stack overflow was a serious source of information in learned how to generate frontend displays, since Eric and Mary Stuart had no experience going in.
* On the backend side, StackOverflow was also the primary source of information. It was used to simplify simple things such as rounding numbers to a certain amount of digits, but it was most useful in learning how to write to properties files. 
* https://stackoverflow.com/questions/4917326/how-to-iterate-over-the-files-of-a-certain-directory-in-java
* https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
* https://stackoverflow.com/questions/22370051/how-to-write-values-in-a-properties-file-through-java-code
## Files Used
### Project Files
* Starting the project: The Main class found in the controller_main package starts the simulation.
* The BackendController class was used for testing purposes in the backend. The main method here helped print values in backend maps as they updated and was used for debugging. Based on our tests, we expect our program to handle most possible errors occurring in the backend and display these to the user through our ErrorHandler class. The frontend also handles file errors that could occur while reading information files for the frontend.
### Data files
#### Backend
* The language folder in the backend resources folder is essential to parsing commands.
* The reference folder contains files (provided by the CS308 website) with the descriptions for each of the non-user-defined commands. This information is organized into a map with is passed to the front end and then used to display the function of each command when the user's mouse hovers over it.
* The variables properties file contains the names and values of all the variables. Since they are stored in a properties file, their values will be carried into the next time the program is run.
* The user command syntax properties file contains the names of the user commands along with the line command that is to be run when it is called. Since they are stored in a properties file, the user commands will be carried into the next time the program is run.
* The user command regex properties file contains one property: UserCommand. All user commands must result in the creation of a UserCommand object, so this property's value is all the user-defined commands' names. Because of this, getSymbol in Parser will be able to create a UserCommand anytime it sees a user-defined command.
* The class locations file contains the location of every Command and InputType class. This is accessed by the factory class which uses reflection to create these objects.

#### Frontend
* The ErrorType.properties file is used to display error messages to th user based on error type.
* The various .css files are used to display different options of background color to the user.
* The .png files are used to display different image options to the user for the turtle.
* history.txt was originally used to store the history of the simulation. This has since moved into an ArrayList.
 
## Project Info
* The Navigation bar offers a variety of options for modifying the display, including the option to change the turtle image into an elephant, fish, and more.
* Any turtle can be made inactive by clicking it, resulting in the turtle image greying out.
* The Environment Info tab offers a live display of turtle variable values and user defined functions. 
* ADD MORE
## Assumptions in Design
* Assumes the Frontend owns an instance of the Backend and therefore, the backend only needs to perform actions on inputs and does not need to call external methods.
* MakeUserInstruction allows creation of invalid command, program will catch error upon running.
* Assumes Tell and Ask will not give the IDs of turtles that have not been created yet. If such IDs are given, the turtles will not be created.
* Assumes user will not attempt to overwrite a previously-written command.
## Known Bugs
* Commands will run in backwards order if they are not nested.
* After creating a user instruction, user must restart the program if they want to use it.
