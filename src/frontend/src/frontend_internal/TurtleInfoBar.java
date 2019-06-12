package frontend_internal;

import frontend_external.Turtle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

public class TurtleInfoBar extends ScrollPaneBar{

    /**
     * Display for this class is an HBox of the turtle info ScrollPane and the pen info ScrollPane.
     * @author Mary Stuart Elder and Eric Werbel
     */

    private ScrollPane myTurtleInfo;
    private ScrollPane myPenInfo;
    // ComboBox for selecting the turtle? and implicitly, the pen
    private ComboBox<Integer> myTurtleSelectionBox;
    private Map<Integer, Turtle> myTurtleMap;
    protected int myTurtleID;
    private TurtleArea myTurtleArea;

    public static final int HORIZONTAL_SEPARATION = 5;
    public static final int VERTICAL_SEPARATION = 5;
    public static final int DEFAULT_TURTLE_ID = 1;
    public static final int PANE_WIDTH = 350;
    public static final int PANE_HEIGHT = 50;
    public static final String ID_LABEL = "Turtle ID";
    public static final String X_POS_LABEL = "X Position";
    public static final String Y_POS_LABEL = "Y Position";
    public static final String HEADING_LABEL = "Heading (Degrees)";
    public static final String PEN_DOWN_LABEL = "Pen Down";
    public static final String PEN_COLOR_LABEL = "Pen Color";
    public static final String TURTLE_BOX_LABEL = "Turtle: ";
    public static final String PEN_THICKNESS_LABEL = "Pen Thickness";
    public static final String TURTLE_TITLE = "Turtle Info";
    public static final String PEN_TITLE = "Pen Info";

    /**
     * Constructor takes in turtles from SlogoView to be notified of changes to the object.
     * On updates to these variables, TurtleInfoBar is updated with newest information.
     * Constructor takes in turtleArea from SlogoView to be able to add Turtles from button.
     * Initializes global variables.
     * @param turtles Map of Turtle ID to turtle
     * @param turtleArea TurtleArea for display of turtles
     */
    public TurtleInfoBar(Map<Integer, Turtle> turtles, TurtleArea turtleArea){
        setTurtleID(DEFAULT_TURTLE_ID);
        myTurtleMap = turtles;
        myTurtleArea = turtleArea;
        myTurtleInfo = makeScrollPane(TURTLE_TITLE, PANE_WIDTH, PANE_HEIGHT);
        myPenInfo = makeScrollPane(PEN_TITLE, PANE_WIDTH, PANE_HEIGHT);
        makeTurtleBox();
        myBar = (HBox) makeDisplay();
    }

    /**
     * Called when the Add Turtle button is pressed to update both the turtle map and the combo box of turtle options.
     * @param turtleMap Map of Turtle ID to turtle
     */
    public void updateTurtleMap(Map<Integer, Turtle> turtleMap){
        myTurtleMap = turtleMap;
        updateComboBox();
    }

    /**
     * Clears the old list of options in the ComboBox, iterates through turtle map and adds all keys as options.
     */
    private void updateComboBox(){
        myTurtleSelectionBox.getItems().clear();
        for(Integer id: myTurtleMap.keySet()){
            myTurtleSelectionBox.getItems().add(id);
        }
    }

    /**
     * On startup, generates ComboBox for turtle selection.
     * Sets listener on ComboBox value, calls onTurtleChange.
     */
    private void makeTurtleBox() {
        myTurtleSelectionBox = new ComboBox();
        updateComboBox();
        myTurtleSelectionBox.valueProperty().addListener((observableValue, oldVal, newVal) -> onTurtleChange(newVal));
    }

    /**
     * Generates a display of information regarding the turtle of the given ID
     * @param turtleID int, turtle ID
     */
    private void buildTurtleDisplay(int turtleID){
        // Build VBox
        VBox turtleInfo = new VBox(VERTICAL_SEPARATION);
        var turtle = myTurtleMap.get(turtleID);
        turtleInfo.getChildren().add(new Text(TURTLE_TITLE));

        turtleInfo.getChildren().add(buildInfoLine(ID_LABEL, Integer.toString(turtleID)));
        turtleInfo.getChildren().add(buildInfoLine(X_POS_LABEL, Double.toString(turtle.getX())));
        turtleInfo.getChildren().add(buildInfoLine(Y_POS_LABEL, Double.toString(turtle.getY())));
        turtleInfo.getChildren().add(buildInfoLine(HEADING_LABEL, Double.toString(turtle.getOrientation())));
        myTurtleInfo.setContent(turtleInfo);
    }

    /**
     * Called to generate a simple line with a label (labelText) and information associated with it (content).
     * @param labelText String text explaining content
     * @param content String text of data
     * @return HBox, line in the display
     */
    private HBox buildInfoLine(String labelText, String content){
        HBox infoBox = new HBox(HORIZONTAL_SEPARATION);
        infoBox.getChildren().addAll(new Label(labelText), new Text(content));
        return infoBox;
    }

    /**
     * Generates a display of information regarding the pen of the turtle of the given ID
     * @param turtleID int, turtle ID
     */
    private void buildPenDisplay(int turtleID){
        // Build VBox
        VBox penInfo = new VBox(VERTICAL_SEPARATION);
        var turtle = myTurtleMap.get(turtleID);
        var pen = turtle.getPen();
        Button penUpDown = makeButton("Switch", e->pen.switchDown());

        penInfo.getChildren().add(new Text(PEN_TITLE));
        penInfo.getChildren().add(buildInfoLine(PEN_COLOR_LABEL, pen.getColor().toString()));
        //turtleInfo.getChildren().add(buildInfoLine(PEN_THICKNESS_LABEL, Double.toString(pen.getThickness())));
        // TODO: add pen thickness option
        penInfo.getChildren().addAll(buildInfoLine(PEN_DOWN_LABEL, Boolean.toString(pen.checkPenDown() == 1)), penUpDown);
        myPenInfo.setContent(penInfo);
    }

    /**
     * Called when the turtle ComboBox changes
     * Updates the current turtleID instance variable, calls methods to update display
     * @param turtleID int, turtle ID
     */
    public void onTurtleChange(int turtleID){
        myTurtleID = turtleID;
        buildTurtleDisplay(turtleID);
        buildPenDisplay(turtleID);
    }

    /**
     * Called when the Add Turtle button is pressed, updates Turtle variables
     */
    private void onTurtleButtonPress(){
        addTurtle(myTurtleMap, myTurtleArea);
        updateTurtleMap(myTurtleMap);
    }

    /**
     * Called from the constructor, builds the HBox holding the overall TurtleInfoBar content.
     * Makes myBar
     * @return HBox the overall display
     */
    @Override
    protected Region makeDisplay() {
        HBox display = new HBox(HORIZONTAL_SEPARATION);
        buildTurtleDisplay(myTurtleID);
        buildPenDisplay(myTurtleID);
        Button addTurtleButton = makeButton("Add Turtle", event -> onTurtleButtonPress());
        display.getChildren().addAll(myTurtleInfo, createSpacer(), new Text(TURTLE_BOX_LABEL), myTurtleSelectionBox, createSpacer(), myPenInfo, createSpacer(), addTurtleButton);
        return display;
    }
}
