package frontend_external;

import frontend_internal.TurtleArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is instantiated every time a Turtle (sprite) is added to the screen.
 * This class contains all methods required to update the Turtle's position and attributes every time the change.
 * This class receives information from other frontend classes (CommandLine, NavBar) detailing how it must update.
 * The Turtle class assumes it will receive the correct information from the classes that call its methods.
 * The actual Turtle object is not passed into the backend, so it updates itself using information passed out of the backend functions.
 *
 * @author Eric Werbel
 */
public class Turtle {

    private Pane myPane;
    private int ID;
    private double myX;
    private double myY;
    private double xOffset;
    private double yOffset;
    private Pen myPen;
    private Set<Line> myLines = new HashSet<>();
    private ImageView myImage;
    boolean active;
    boolean showing;
    private double opaque = 1.0;
    private double translucent = 0.3;

    /**
     * Creates a new Turtle object at a given x and y position and creates a new Pen object to correspond to the Turtle.
     * Additionally, it sets the start image to be of a Turtle.
     */
    public Turtle(TurtleArea turtleArea, int id) {
        ID = id;
        myPane = (Pane) turtleArea.getDisplay();
        active = true;
        showing = true;
        myX = 0;
        myY = 0;
        myPen = new Pen();
        myImage = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Turtle.png")));
        turtleArea.getDisplay().widthProperty().addListener((observable, oldvalue, newvalue) ->
                setOffset((Double)newvalue/2 - myImage.getFitWidth()/2, yOffset));
        turtleArea.getDisplay().heightProperty().addListener((observable, oldvalue, newvalue) ->
                setOffset(xOffset, (Double)newvalue/2 - myImage.getFitHeight()/2));
        //turtleArea.initializeTurtle(this);
        myImage.addEventHandler(MouseEvent.MOUSE_CLICKED, e->switchActive());
    }

    /**
     * @return the id corresponding to the Turtle object
     */
    public int getId() { return ID; }

    /**
     * Checks whether the Turtle is currently "active" using the boolean variable active.
     * Returns a double so that the value can be added to the TurtleInfo map that is passed to the backend.
     *
     * @return a double 1.0 if the Turtle is active and 0.0 if is is inactive
     */
    public double isActive() {
        if (active) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    /**
     * Sets the current active status of the Turtle to the boolean value passed in as a parameter
     *
     * @param newStatus = boolean value stating whether the Turtle's active status is true or false
     */
    public void setActive(boolean newStatus) {
        active = newStatus;
        activeImage();
    }

    /**
     * Toggles the active status of the Turtle, if false it is set to true and vice versa
     */
    private void switchActive() {
        active = !active;
        activeImage();;
    }

    /**
     * Checks whether the Turtle is currently being displayed on the screen using the boolean variable showing.
     * Returns a double so that the value can be added to the TurtleInfo map that is passed into the backend.
     *
     * @return a double 1.0 if the Turtle is showing and 0.0 if the Turtle is hiding
     */
    public double isShowing() {
        if (showing) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    /**
     * Sets the current showing state of the Turtle based on a parameter passed into the method
     *
     * @param newStatus = boolean value stating the new "showing" status of the Turtle
     */
    public void setShowing(boolean newStatus) {
        showing = newStatus;
        myPane.getChildren().remove(this.getTurtleView());
        if (showing) {
            myPane.getChildren().add(this.getTurtleView());
        }
    }

    /**
     * Sets the Turtle's ImageView based on the current active status of the Turtle.
     * If inactive, make the Turtle more translucent, if active, make it solid.
     */
    private void activeImage() {
        if (active) {
            myImage.setOpacity(opaque);
        } else {
            myImage.setOpacity(translucent);
        }
    }

    /**
     * @return the ImageView of the Turtle that is actually displayed on the screen
     */
    public ImageView getTurtleView(){
        return myImage;
    }

    /**
     * @return the Pen object corresponding to the current Turtle
     */
    public Pen getPen() { return myPen; }

    /**
     * Sets the offset of the Turtle so that the 0,0 coordinate is always in the center of the Turtle Display Area.
     * Each time the screen is resized, this method is called to keep the Turtle in the correct position on the screen.
     * Method  alters the offset of lines corresponding to the Turtle so that it always connects ot the Turtle's current position.
     *
     * @param xOff = the amount the Turtle needs to be shifted in the x direction
     * @param yOff = the amount the Turtle needs to be shifted in the y direction
     */
    public void setOffset(double xOff, double yOff) {
        var diffXOffset = xOff - xOffset;
        var diffYOffset = yOff - yOffset;
        xOffset = xOff;
        yOffset = yOff;
        setX(myX);
        setY(myY);
        for(Line l: myLines){
            l.setStartX(l.getStartX() + diffXOffset);
            l.setStartY(l.getStartY() + diffYOffset);
            l.setEndX(l.getEndX() + diffXOffset);
            l.setEndY(l.getEndY() + diffYOffset);
        }
    }

    /**
     * Updates the image of the sprite on screen
     *
     * @param imageName is a String that matches the file name of the new image
     */
    public void setImage(String imageName) {
        myImage.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(imageName)));
    }

    /**
     * Rotates the image by the amount specified in the parameter.
     * Positive values rotate counter clockwise.
     * Negative values rotate clockwise.
     *
     * @param rotateValue = the amount to rotate the image
     */
    public void updateOrientation(double rotateValue) {
        myImage.setRotate(rotateValue);
    }

    /**
     * @return the current orientation of the Turtle
     */
    public double getOrientation() {
        return myImage.getRotate();
    }

    /**
     * @param x = new x position of the Turtle
     */
    public void setX(double x) {
        myX = x;
        myImage.setX(x+xOffset);
    }

    /**
     * @param y = new y position of the Turtle
     */
    public void setY(double y) {
        myY = y;
        myImage.setY(y+yOffset);
    }

    /**
     * @return the current x position of the Turtle
     */
    public double getX() { return myX; }

    /**
     * @return the current y position of the Turtle
     */
    public double getY() { return myY; }

    /**
     * Draws a line from one point to another.
     * This method is called after the backend has finished running.
     * Draws a line from the Turtle's start position to each point in a list given by the backend.
     *
     * @param startX = beginning x coordinate of the line
     * @param startY = beginning y coordinate of the line
     * @param endX = ending x coordinate of the line
     * @param endY = ending y coordinate of the line
     */
    public void drawLine(double startX, double startY, double endX, double endY) {
        Line myLine = new Line();
        double imgSize = myImage.getFitHeight() / 2;
        if (myPen.checkPenDown() == 1) {
            myLine.setStroke(myPen.getColor());
            myLine.setStartX(startX+xOffset+imgSize);
            myLine.setStartY(startY+yOffset+imgSize);
            myLine.setEndX(endX+xOffset+imgSize);
            myLine.setEndY(endY+yOffset+imgSize);
        }
        myLines.add(myLine);
        myPane.getChildren().add(myLine);
    }

    /**
     * Removes all lines corresponding to the given Turtle from the screen
     */
    public void clearPath() {
        for (Line l : myLines) {
            myPane.getChildren().remove(l);
        }
    }

}

