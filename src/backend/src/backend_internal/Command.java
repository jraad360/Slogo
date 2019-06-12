package backend_internal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Command implements the interface InputType and the superclass for all types of commands.
 * @author Jamie Palka and Jorge Raad
 */
public abstract class Command implements InputType {
    public static final int DECIMAL_PLACES = 5;

    public static final String X = "x";
    public static final String Y = "y";
    public static final String ORIENTATION = "orientation";
    public static final String ACTIVE = "active";
    public static final String ID = "id";
    public static final String PEN_COLOR = "pencolor";
    public static final String PEN_DOWN = "penstate";
    public static final String SHOWING = "showing";
    public static final String RESET_PATH = "resetpath";

    protected List<InputType> myParameters;
    public static Parser myParser;
    protected int myParamNumber;

    // I realize now that this was really bad design but I imagine the point of the assignment isn't to go back and fix
    // our design choices, so I continue to make some back design decisions so I won't have to redo everything
    public static List<Map<String, Double>> LIST_OF_TURTLES;
    public static List<ArrayList<Double>> POINTS;
    public static Map<String, Double> CURRENT_TURTLE_MAP;
    public static List<Double> STAMPS;

    protected String myCommandName;

    /**
     * This constructor initializes the list that will be used to store its parameters.
     */
    public Command() {
        myParameters = new ArrayList<>();
    }


    @Override
    public int getParamNum(){
        return myParamNumber;
    }

    @Override
    public void setParameter(InputType parameter) {
        myParameters.add(parameter);
    }

    // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places

    /**
     * Sets the Command's name. This information is only useful for UserCommands in order to distinguish between
     * different user-defined commands.
     * @param parameter - (String) Command name
     */
    @Override
    public void setParameter(String parameter){
        myCommandName = parameter;
    }

    /**
     * Rounds number to the set number of decimal places.
     * @param value - (double) number to be rounded
     * @return - (double) rounded result
     */
    public static double round(double value) {
        if (DECIMAL_PLACES < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Throws an IllegalArgument Exception if there is a problem with the given arguments. Always called from within the
     * eecute method. Checks for any errors with the given parameters. Makes sure there enough parameters for the
     * Command to be executed.
     * @throws IllegalArgumentException
     */
    protected void checkError() throws IllegalArgumentException{
        if(myParameters.size() < myParamNumber){
            throw new IllegalArgumentException("Missing arguments.");
        }
    }

    protected void addPoint (double x, double y){
        int turtleID = (int)getValue(ID);
        POINTS.get(turtleID).add(x);
        POINTS.get(turtleID).add(y);
    }

    protected double getValue(String key){
        return CURRENT_TURTLE_MAP.get(key);
    }

    protected void setValue(String key, Double value){
        CURRENT_TURTLE_MAP.put(key, value);
    }

    protected Map<String, Double> getTurtle(int myID){
        for(Map<String, Double> turtle : LIST_OF_TURTLES){
            if((double)turtle.get(ID) == (double) myID){
                return turtle;
            }
        }
        return null;
    }
}