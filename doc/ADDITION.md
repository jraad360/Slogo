CompSci 308: SLogo Addition
===

# Before

### Estimation

 * How long do you think it will take you to complete this new feature?
    I think it will take half an hour

 * How many files will you need to add or update? Why?
    I will need to add two Command classes and will need to update the file that contains their location to use reflection.
    I may also need to edit some stuff in the backend controller and some stuff in the frontend.


# After

### Review

 * How long did it take you to complete this new feature?
 
    2 hours (correct Command output, but could not figure out how to display stamp on frontend)

 * How many files did you need to add or update? Why?
 
    I had to add 2 classes that extended Command and add their class locations to the property file for reflection. This enabled
    these new Stamp and ClearStamps Classes to be made. I additionally had to change the BackendController.
    
 * Did you get it completely right on the first try? If not, why not?
 
    No because I could not figure out the stuff on the front end to display the stamp.

### Analysis

 * What do you feel this exercise reveals about your project's design and documentation --- was it as good (or bad) as you remembered?
  
    I think we did do a good job documenting and mostly designing. The public static variables I used in Command though 
    was much worse than I thought at the time. Maybe it's because I wasn't in the front end but it was very difficult to 
    figure out where to add the stamp ImageViews.


 * What about the design or documentation could be improved?
 
    Most of our design was decent but we definitely should have avoided the use of the static variables in the Command. 
    I definitely made a huge mistake with design in order to get better functionality. I 
    definitely should have figured out a different way to give Commands access to all of the turtle info. Now with my 
    experience I think I would have chosen to have the InputTypeFactory inject the necessary information into the
    multi-turtle Commands. I think I did a good job with documentation though because I figured most of it out quick. 

 * What would it have been like if you were not familiar with the code at all?
 
    Despite it having been so long, I quickly began to remember everything. If I had never seen this code, it would have
    taken a much longer time.