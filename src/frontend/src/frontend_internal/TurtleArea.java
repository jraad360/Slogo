package frontend_internal;

import frontend_external.Turtle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.Map;

public class TurtleArea extends Display {

    /**
     * Extends Display in order to access makeDisplay method and, if desired in future expansion, buttons.
     * This class contains the simulation portion of Slogo. Multiple turtles may move, rotate, and change in this area and will be maintained in this class.
     * Offers a public method for initializing a turtle in the display with the proper offset.
     * Does not assume a non-null map: a null map will be able to function fine in this constructor.
     * @author Mary Stuart Elder and Eric Werbel
     */

    private Map<Integer,Turtle> myTurtles;
    private Pane myTurtleArea;

    public static final int SIMULATION_SIZE_X = 300;
    public static final int SIMULATION_SIZE_Y = 200;

    /**
     * Constructor, takes in Map of turtles from SlogoView to display updates to turtles.
     * Adds one initial turtle to display.
     * @param turtles Map of Turtle ID to turtle
     */
    public TurtleArea(Map<Integer,Turtle> turtles){
        myTurtles = turtles;
        myTurtleArea = (Pane) makeDisplay();
        addTurtle(myTurtles, this);
    }

    /**
     * Can be called by any Display subclass, since the addTurtle method in display calls this method.
     * Given a non-null Turtle and a non-null myTurtleArea, initializes the offset of the turtle to center it in the display.
     * @param myTurtle Turtle to be added to the Area
     */
    public void initializeTurtle(Turtle myTurtle){
        double xOff = myTurtleArea.widthProperty().get()/2 - myTurtle.getTurtleView().getFitWidth()/2;
        double yOff = myTurtleArea.heightProperty().get()/2 - myTurtle.getTurtleView().getFitHeight()/2;
        myTurtle.setOffset(xOff, yOff);
    }

    /**
     * Called from SlogoView to display the turtle area.
     * @return Pane, myTurtleArea
     */
    public Region getDisplay(){
        return myTurtleArea;
    }

    /**
     * Called in Constructor to make initial display.
     * @return Pane, used to initialize myTurtleArea
     */
    @Override
    protected Region makeDisplay() {
        var turtlePane = new Pane();
        turtlePane.setPrefSize(SIMULATION_SIZE_X, SIMULATION_SIZE_Y);
        return turtlePane;
    }
}
