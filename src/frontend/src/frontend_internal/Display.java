package frontend_internal;

import frontend_external.Turtle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.Map;

/**
 * This class is an abstract class that applies to all different objects being displayed to the user.
 * The main purpose of the class is to provide a place for common methods that are used in multiple different display areas.
 * This class only depends on other classes to pass in the correct parameters into its methods.
 *
 * @author Mary Stuart Elder and Eric Werbel
 */
public abstract class Display {

    public static final int TURTLE_SIZE = 50;
    private int turtleID = 0;

    /**
     * Returns the display object for each component of the frontend.
     *
     * @return the actual object that is displayed to the user from each subclass
     */
    protected abstract Region makeDisplay();

    /**
     * Sets the next Turtle id to the value passed in as a parameter
     *
     * @param id = new Turtle id
     */
    protected void setTurtleID(int id){
        turtleID = id;
    }

    /**
     * Creates a button that will call the method passed in as a parameter.
     * Button will display its own name and can be visible in the region it is created in.
     *
     * @param property = button name to be displayed
     * @param handler = method to be called by the button when pressed
     * @return a Button object to be displayed that will call the method passed in as a parameter
     */
    protected Button makeButton (String property, EventHandler<ActionEvent> handler) {
        var result = new Button();
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }

    /**
     * This method adds a Turtle to the map of Turtles with a proper ID.
     * This also takes care of initializing the Turtle's display on the correct Pane and creating its initial state.
     *
     * @param myTurtles = current map of Turtles, meant to be altered to accommodate new Turtle
     * @param myTurtleArea = Pane where all Turtles are displayed
     */
    protected void addTurtle(Map<Integer,Turtle> myTurtles, TurtleArea myTurtleArea) {
        Turtle t = new Turtle(myTurtleArea, turtleID);
        Pane turtlePane = (Pane) myTurtleArea.getDisplay();
        myTurtles.put(turtleID, t);
        turtleID++;
        t.getTurtleView().setFitWidth(TURTLE_SIZE);
        t.getTurtleView().setFitHeight(TURTLE_SIZE);
        myTurtleArea.initializeTurtle(t);
        turtlePane.getChildren().add(t.getTurtleView());
    }
}

