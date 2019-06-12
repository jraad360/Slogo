package frontend_internal;

/**
 * @author Eric Werbel
 *
 * This interface contains all methods that allow different fronend elements to communicate with one another.
 * This will make it easier to display multiple objects on the screen and keep track of the current state of the display.
 * More methods will likely be added as we begin to code and recognize what information needs to be communicated between objects.
 */
interface InternalFrontEnd {

    /**
     * This method allows the main class controlling the display to get the size of all object on the display.
     * This is important for determining which objects should go where and ensuring there is no overlap.
     * Other objects can also call this method to help determine their own position.
     *
     * @return the size of the object
     */
    public int getSize();

    /**
     * This method will return the position of any object on the screen.
     * This will make it easier to track where everything is on the screen and make it easy to track the relation between different objects as well.
     *
     * @return an array of two doubles containing the x and y position of an object
     */
    public double [] getPosition();

}