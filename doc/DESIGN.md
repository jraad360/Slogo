# Design: Slogo

#### Internal Frontend
 The majority of these classes fall into the category of Display subclasses. Display subclasses are able to make buttons, make displays (some type of Region), and add turtles to the TurtleArea. This allowed for flexibility in creating regions in the layout with buttons and turtle modifying capabilities. Further hierarchies within the Display subclass followed, namely the Bar class and its subclasses. Bar classes have display regions in the form of an HBox and contain a method (createSpacer) for evenly spacing the components of the Bar. This is useful for the organizing tabs in the TabPane at the top of the scene (TabController), since all tabs can be made from the HBox of the Bars put into TabController. Most Bars in the project were put in the TabController, however, the CommandLine (also an HBox based structure) was placed at the bottom of the Scene given the importance of the terminal for executing commands. Finally, the TurtleArea in the center of the screen maintains the running and placement of turtles in the display. 
 
#### External Frontend
This portion of the project largely consists of the Turtle and Pen classes. The backend provides updated values and variables associated with the Turtle and Pen. These classes have public methods called by the internal frontend to update the position, orientation, image, etc. of the Turtle and Pen objects with information from the backend. If the backend encounters issues and must throw an error of some sort, the external frontend uses its ErrorHandler class to catch and display this error to the user in a friendly way.

#### Internal Backend
* InputType is an interface that represents all possible types of input a user can type. The classes that extend it include Word, Variable, Constant, Command, and BracketList. 
* There is a properties file that stores all variables available in the environment. 
* Properties files for the regular expressions and syntax of default and user-created commands hold the data needed for reflection in the InputType Factory.
* A BracketList is used for complex commands and is simply defined as input surrounded by brackets.
* Command is the parent class for all types of Commands, which each implement their own execute() function which changes state and return the result value. The Command class also holds a list of maps which represent all of the data for each turtle that exists, such as its active state, id, x and y values, etc so that it can be updated in the commands that impact the turtle.
* The Parser is the class that reads user input and modularizes it into a queue of stacks for use. It supports nested commands.
* The CommandRunner uses the formatted data the Parser creates from user input and contains functions for running it.
* The InputTypeFactory utilizes reflection to create the correct InputType. It is used by the parser.
* The PropertiesFileWriter allows one to read to and from the multiple properties files we utilize in our code.
* The BackendController implements the backend_external API, which defines all public methods that can be used by the frontend.

#### External Backend

* The BackendController class owns its own CommandRunner.
* The run() method is what initiates the execution of user input. If the current command is ASK or TELL, those respective commands are executed using parseToExecute() in the CommandRunner. If not, the command is run for each active turtle, AKA the “current” turtle for each loop, checking that it is active.
* Other methods allow for the frontend to retrieve and update data that the backend holds regarding commands, the language, variables, and the existent turtles.

## Adding New Features

### Backend

To add a more complex user-defined command that is independent of the original commands, one create a subclass of Command, add the class’s name and syntax it will recognize to the language properties file, and add the class’s path to class_locations.properties.

### Frontend
Most new components in the front end can be added through the TabController as new a type of Bar. For example, if a user wanted to add a tab with stylesheet details and the ability to modify its contents, they would create a subclass of Bar with access to the stylesheet currently used in the scene. This Bar needs to be initialized before TabController and added as an input to its constructor for it to be added as a tab. This process could easily be simplified by changing the constructor input to the TabController into a list of Bars.

## Design Choices

* The APIs represent an outside view of code that encapsulates its functionality and hides the implementation of fundamental logic. None of the API methods actually implement any important logic that is fundamental to the flow of the code. Rather, it simply allows for the usage of the results of said logic and data transfer. Therefore, it would be very easy to completely re-work the backend_external API without making any other large changes to the logic of the backend. For example, see the code for getUserCommands() and run(), both implemented in the BackendController class which implements the backend_external interface. getUserCommands() doesnt implement any logic regarding creating or managing commands, rather, it simply presents the data. Similarly, the run() method makes calls to the backend_internal interface which initiates the logic for the execution of commands, but the run() method does not itself implement this. This level of modularity and encapsulation allows for our code to be easily extendable while allowing for steady communication between the frontend and backend.

* InputType is an interface that represents all possible types of input a user can type. The classes that extend it include Word, Variable, Constant, Command, and BracketList. This interface has functions for getting the number of parameters of the type (which is hard coded as 0 for all types other than Commands), setting the parameters for the type, and executing the type. There are two setParameter() functions - one which takes an InputType and is used by Commands, and one which takes a String and used by all the other types.

* The Parser is the class that reads user input and modularizes it for use. The Parser uses a queue of stacks to represent the order of user input. Each queue entry (stack) represents a different line of user input (referred to as an input unit). Each "input unit" is comprised input types. It is assumed that the user will type commands (nested or not) on one line, and that different commands will be on different lines. The InputType factory, as well as the resource files provided are used by the createInputUnitStack() function to create all the correct objects for each piece of input. If the line contains brackets, the function createComplexInputUnitStack() is called, which has the functionality of adding BracketLists to the stack (checking for nested BracketLists).

* The CommandRunner uses the formatted data the Parser creates from user input and contains functions for running it. It contains its own Parser object. The class contains the function parseToExecute() which executes the content of the queue created for user input. It also has a function for setting the language, which is ultimately done in the Parser. On a high level, this moves through the queue, and for each stack, utilizing a temporary stack, it does the following:

     * It moves through the stack, and for each element, it checks to see if the InputType object is of type command. If so, it sets all elements above the command (now in the temporary stack) as the parameters for the command using the setParameter(InputType parameter) function. It then executes that command using its execute() function and saves its value in the temporary stack. This process repeats, accounting for nested commands.
     
 * The InputType class is a great example of the encapsulation and implementation hiding in our program. Each InputType completely encapsulates its own functionality, and since all InputTypes have their own execute functions, high level complex commands don’t need to worry about the contents of their BracketLists or parameters - they can just call their execute() functions. To go even farther, BracketLists don’t even need to worry about their contents or nested commands, because that is also taken care of on a level below when the queue of stacks is created and ran. This modularity and implementation hiding makes the code very extendable and simple at a high level. This concept was challenging to create because it took a lot of planning ahead to make sure it would work throughout the whole project, but with careful planning, we made it work.
 
 * One important design decision we made is the creation of the BracketList. An alternate design for this was to, within the BracketList class, have its own Parser that further modularizes the inner string without assuming it is only commands, like our code currently does. I think this would have a been a better design decision, because when we did the complex commands, we had to implement string logic in those commands in order to work with the contents of the BracketLists, but it would have been better to have a more comprehensive execute() method or even make subclasses of BracketList so that implementation was encapsulated by the BracketList and hidden. The only pro of our current code was that it took less time to plan and write, but it is not extendable.
   
  * Another important design decision we made is the lack of a use of a frontend_external API (it physically exists, but is just never utilized by the backend and does not present useful methods to the backend). To recap, in hindsight, this is bad practice, as all communication functionality is put on the frontend and its utilization of the backend_external API. This should have been broken up. For example, rather than having the frontend both give the backend updated data and update its own data using the backend_external methods, we should have had the backend call a frontend_external interface in order to retrieve updated turtle data, rather than getting it through its own external interface. An external interface is what should be used to pass data out of the given section of the project. Some sort of frontend_external API  should have had a method that would allow backend to get the data currently held on the frontend. The frontend calls the updateTurtleData() method in backend_external to pass the data to the backend from an external backend interface. Backend should call a method to request the data from within a backend class and then using the front end interface (which has access to the data) the data could be passed to the backend.

## Assumptions Made

* An important assumption made is that all related user input will be typed on one line. For example, one full command, even if it is nested, will be typed with no new line characters.