package frontend_external;

/**
 * @author Eric Werbel
 *
 * This interface contains all the functions located in the frontend that are called from the backend.
 * These methods allow the backend to get current data from the frontend and execute logic using this data.
 */
public interface ExternalFrontEnd {

    // Methods affecting turtle
    /**
     * This function sets the current x position of the turtle.
     * After the backend conducts logic/executes the functions, it can call this method to update the position of the turtle.
     * This allows the frontend display to stay up to date with the backend logic.
     *
     * @param x = new x position of turtle
     */
    public void setX(double x);

    /**
     * This function sets the current y position of the turtle.
     * After the backend conducts logic/executes the functions, it can call this method to update the position of the turtle.
     * This allows the frontend display to stay up to date with the backend logic.
     *
     * @param y = new y position of turtle
     */
    public void setY(double y);

    /**
     * This method can be called to determine the current x position of the turtle.
     * Knowing the current x position allows the backend to carry out logic and determine the new position based on what functions are called.
     *
     * @return the x position of a given object (mostly the Turtle)
     */
    public double  getX();

    /**
     * This method can be called to determine the current y position of the turtle.
     * Knowing the current y position allows the backend to carry out logic and determine the new position based on what functions are called.
     *
     * @return the y position of a given object (mostly the Turtle)
     */
    public double getY();

    /**
     * This method can be called form the backend to change which way the turtle is facing on the screen.
     * After a rotate command is called, the turtle will need to face in a different direction.
     * This method allows the frontend to stay up to date with the backend.
     *
     * @param orientation = new orientation of the turtle
     */
    public void updateOrientation(double orientation);

    /**
     * Allows the backend to obtain information about the current orientation of the turtle.
     * This is needed to determine new orientations after a rotate and to determine the direction the turtle should move.
     * Anytime the turtle's position is updated, it will need to know the current orientation.
     *
     * @return the current orientation of the turtle
     */
    public double getOrientation();

    // User Input
    /**
     * This method must be called from the backend parser in order to "decode" the user's input.
     * This method allows the frontend to pass the backend the information that is necessary to execute specific functions.
     * This allows the frontend and backend to remain compartmentalized, as after the String is passed to the backend,
     * the frontend does not have to worry about any of the implementation or logic.
     *
     * @return a string matching the user input from the command line/text box
     */
    public String getUserInput();

    // Error Display

    /**
     * This method allows the backend to let the user know if there was bad input.
     * Instead of throwing an exception or crashing the program, this will alert the user to the mistake and allow them a chance to correct it.
     * Since the error checking occurs in the backend, the frontend needs a method that will allow it to display the results of the error checking,
     * so the user knows how to fix their input.
     *
     * @param errorType = a String specifying what type of error must be shown to the user
     */
    public void showError(String errorType);

}