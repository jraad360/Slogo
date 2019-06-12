package frontend_external;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Each Turtle object controls a Pen object which is responsible for drawing the lines to track the Turtle's movement.
 * The Pen class contains information about whether the pen is currently down or not and its color.
 * Each Pen assumes that the Turtle has the correct position information and lets the Turtle initiate the drawing of the line as a result.
 *
 * @author Eric Werbel
 */
public class Pen {

    Color myColor;
    boolean isDown;

    /**
     * Initializes a pen object that allows Turtles to draw lines on the display
     */
    public Pen() {
        myColor = Color.BLACK;
        isDown = true;
    }

    /**
     * Sets the new color for the Pen to draw
     *
     * @param newColor = new color for the pen
     */
    public void setColor(Color newColor) { myColor = newColor; }

    /**
     * @return the current color of the Pen
     */
    public Color getColor() { return myColor; }

    /**
     * Sets whether the Pen is down or not
     *
     * @param newStatus = boolean representing whether the pen is down or not
     */
    public void setDown(boolean newStatus) { isDown = newStatus; }

    /**
     * Switches the current pen down state, if down, set to up and vice versa
     */
    public void switchDown() { isDown = !isDown; }

    /**
     * Checks if the pen is currently down
     *
     * @return boolean representing whether the pen is down or not
     */
    public double checkPenDown() {
        if (isDown) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

}
