package frontend_internal;

import backend_external.ExternalBackend;
import frontend_external.ErrorHandler;
import frontend_external.Turtle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.*;

/**
 * This class is the main place where the frontend communicates with the backend.
 * Users can input commands into the text field included in this object and run them through the backend.
 * There are also buttons that allow users to click to move the Turtle forward, backward, left, and right.
 * This class takes in a backend interface and assumes that the backend will return the correct information to update each turtle.
 * It also is dependent on having an updated list of current Turtles in order to keep all Turtles current and up to date.
 * This is only instantiated once as it is a display, but the buttons call methods that can be run at any point in the program.
 *
 * @author Mary Stuart Elder and Eric Werbel
 */
public class CommandLine extends Bar{

    private TextField myCommandLine;
    private CommandHistory myHistory;
    private ExternalBackend myBackend;
    private Map<Integer,Turtle> myTurtles;
    private TurtleInfoBar myTurtleInfoBar;

    protected ObservableMap<String, String> myVariablesMap = FXCollections.observableHashMap();
    protected ObservableMap<String, String> myUserCommandsMap = FXCollections.observableHashMap();


    /**
     * Instantiates the CommandLine object to be displayed to the user.
     * Includes an area for users to type commands and buttons enabling the Turtle to move forward, backward, and turn left and right.
     * Uses the backend interface to run the requested commands by passing the user text.
     *
     * @param history = command history object where new commands will be added
     * @param turtles = map of current Turtles tracked by the program
     * @param infoBar = bar at the top displaying the current turtle data
     * @param backend = backend interface allowing functions to be run
     */
    public CommandLine(CommandHistory history, Map<Integer,Turtle> turtles, TurtleInfoBar infoBar, ExternalBackend backend) {
        myBackend = backend;
        myTurtles = turtles;
        myHistory = history;
        myTurtleInfoBar = infoBar;
        myBar = (HBox) makeDisplay();
    }

    /**
     * @return the text field where users can input their commands
     */
    public TextField getTextField() {
        return myCommandLine;
    }

    /**
     * Builds a map of all the Turtle information for a given turtle to be passed into the backend.
     * Instead of passing Turtle objects, we pass all the necessary data corresponding to each turtle.
     *
     * @param turtleMap = map to be filled with the data from the current Turtle
     * @param t = current Turtle object
     */
    private void buildTurtleMap(Map<String, Double> turtleMap, Turtle t){
        turtleMap.put("id", (double)t.getId());
        turtleMap.put("x", t.getX());
        turtleMap.put("y", t.getY());
        turtleMap.put("orientation", t.getOrientation());
        turtleMap.put("resetpath", 0.0);
        turtleMap.put("active", t.isActive());
        turtleMap.put("showing", t.isShowing());
        turtleMap.put("penstate", t.getPen().checkPenDown());
    }

    /**
     * Method is called whenever the execute button is pressed.
     * Passes current text input by the user to the backend to actually run the functions.
     *
     * @param userInput = text input by the user to run a function
     */
    private void storeAndExecute(TextField userInput){
        try{
            List<Map<String,Double>> turtleInfo = new ArrayList<>();
            for (Turtle t : myTurtles.values()) {
                Map<String,Double> turtleMap = new HashMap<>();
                buildTurtleMap(turtleMap, t);
                turtleInfo.add(turtleMap);
            }
            myBackend.updateTurtleData(turtleInfo);
            double output = myBackend.run(userInput.getText());
            System.out.println(output);
            List<ArrayList<Double>> myPoints = myBackend.getPoints();
            List<Double> stamps = myBackend.getStamps();
            for (Map<String,Double> map : (myBackend.getTurtleInfo())) {
                int currID = (int)(map.get("id")/1);
                if (stamps.contains((double) currID)){
                    // probably show up repeatedly
                    // add stamp

                }
                Turtle t = myTurtles.get(currID);
                updateTurtle(t, map, myPoints.get(currID));
            }
            updateMaps();
            myTurtleInfoBar.onTurtleChange(myTurtleInfoBar.myTurtleID);
        }
        catch(IllegalArgumentException e){
            new ErrorHandler("incorrectParams");
        }
        catch(IllegalStateException e) {
            new ErrorHandler("invalidFunction");
        }
        myHistory.storeAndClearTextField(userInput);
    }

    /**
     * After backend runs, this method is called to update the state of all Turtles.
     *
     * @param t = current Turtle to be updated
     * @param map = map containing information about the new state of the Turtle
     * @param points = a list of all the points the Turtle traveled to
     */
    private void updateTurtle(Turtle t, Map<String,Double> map, List<Double> points){
        double x0 = t.getX();
        double y0 = t.getY();
        for (int i = 0; i < points.size(); i+=2) {
            double x1 = points.get(i);
            double y1 = points.get(i+1);
            t.drawLine(x0, y0, x1, y1);
            x0 = x1;
            y0 = y1;
        }
        t.setX(map.get("x"));
        t.setY(map.get("y"));
        t.updateOrientation(map.get("orientation"));
        t.setActive(map.get("active") == 1);
        t.setShowing(map.get("showing") == 1);
        t.getPen().setDown(map.get("penstate") == 1);
        if (map.get("resetpath") == 1) {
            t.clearPath();
        }
    }

    /**
     * Updates the list of variables and user commands after the backend has made changes
     */
    private void updateMaps(){
        var functionMap = myBackend.getVariables();
        updateMap(functionMap, myVariablesMap);

        var userMap = myBackend.getUserCommands();
        updateMap(userMap, myUserCommandsMap);
    }

    /**
     * Updates the frontend map to keep it consistent with the state of the backend.
     *
     * @param backendMap = map passed form the backend with the new values
     * @param localMap = frontend map that must be updated using the map from the backend
     */
    private void updateMap(Map<String, String> backendMap, Map<String, String> localMap){
        for(Map.Entry<String, String> fcn: backendMap.entrySet()){
            localMap.put(fcn.getKey(), fcn.getValue());
        }
    }

    /**
     * Creates the actual region to be displayed to the user.
     * Combines all objects/buttons into one object to be shown to the user.
     *
     * @return a region containing all information related tot eh command line display
     */
    @Override
    protected Region makeDisplay() {
        var hBox = new HBox(8); // spacing = 8
        myCommandLine = new TextField();

        Button execute = makeButton("Execute", event -> storeAndExecute(myCommandLine));
        Button left = makeButton("Left", event -> storeAndExecute(new TextField("lt 90")));
        Button fd = makeButton("Forward", event -> storeAndExecute(new TextField("fd 10")));
        Button back = makeButton("Backward", event -> storeAndExecute(new TextField("back 10")));
        Button right = makeButton("Right", event -> storeAndExecute(new TextField("rt 90")));
        hBox.getChildren().addAll(createSpacer(), new Label("Command Line:"), myCommandLine, execute, createSpacer(), left, fd, back, right);
        return hBox;
    }
}
