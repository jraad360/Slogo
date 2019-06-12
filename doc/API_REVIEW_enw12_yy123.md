### Summary of Other Design
* uses listeners/observers to determine when values have changed and set new
* returns next states from functions (x,y, pen up?, colors)
    * each state has all necessary info for display to update
* function interface that contains basic methods common for all functions

### Part 1
* good idea to have "states" that contain all information for front end
* should allow user more ability to interact with front end 
    * more flexible so user interaction doesn't mess with overall display
* frontend and backend are entirely separate and hidden from each other
* frontend and backend rely on correct information being passed but don't care where it comes from
* need to learn more about frontend design/javafx before I can fully say what should be encapsulated
* lots of exceptions from parsing 
    * if throw an exception from parser, front end needs to show resulting error message
    * show error message in new window 
    * pull up new window will be easier than altering what is displayed on screen
* design is good because when add a new function to the map, if iterating through all keys in map to create button/do something, it will automatically do it when the function is added

### Part 2
* very excited to learn JavaFx and figure out how all the classes work together
    * excited to see how everything comes together to create one display
* most worried about learning JavaFx, not experience 
      