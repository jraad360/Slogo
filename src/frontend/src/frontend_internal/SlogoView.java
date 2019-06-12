package frontend_internal;

import backend_external.ExternalBackend;
import frontend_external.Turtle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;
import java.util.Map;

public class SlogoView {

    /**
     * This class puts together the overall layout of the Slogo scene.
     * Public methods are called from the FrontendController.
     * Initializes and places display components of the simulation with action from the backend.
     * @author Mary Stuart Elder and Eric Werbel
     */

    private Scene myScene;
    private CommandLine myCommandLine;
    private CommandHistory myHistory;
    private BorderPane myRoot;
    private TurtleArea myTurtleArea;
    private TurtleInfoBar myTurtleInfoBar;
    private ExternalBackend myBackend;
    private NavBar myNavBar;
    private Map<Integer,Turtle> myTurtles = new HashMap<>();

    public static final int SCENE_SIZE_X = 1000;
    public static final int SCENE_SIZE_Y = 600;
    public static final String DEFAULT_STYLE = "Blue.css";

    /**
     * Constructor.
     * Takes in ExternalBackend interface from controller for reference to its methods.
     * Initializes global variables and builds the overall scene displayed to the user by calling buildScene.
     * Sets style of sheet.
     * @param backend ExternalBackend interface used in the controller
     */
    public SlogoView(ExternalBackend backend){
        myBackend = backend;
        myHistory = new CommandHistory();
        myScene = buildScene();
        myScene.getStylesheets().add(DEFAULT_STYLE);
    }

    /**
     * Generates the overall scene for the simulation and knits in the ExternalBackend where needed for communication.
     * Listeners are used for inter-class communication as properties are changed.
     * @return Scene, fully formatted initial scene with Turtle display, command line, and tabs
     */
    private Scene buildScene(){
        myRoot = new BorderPane();

        myTurtleArea = new TurtleArea(myTurtles);
        myRoot.setCenter(myTurtleArea.getDisplay());

        myTurtleInfoBar = new TurtleInfoBar(myTurtles, myTurtleArea);

        myCommandLine = new CommandLine(myHistory, myTurtles, myTurtleInfoBar, myBackend);
        EnvironmentInfoBar infoBar = new EnvironmentInfoBar(myCommandLine, myBackend);
        myRoot.setBottom(myCommandLine.getBar());

        myNavBar = new NavBar(myHistory, myTurtles);
        myNavBar.myStyle.addListener((observableValue, s, t1) -> updateStyle(s, t1));
        myNavBar.myLanguage.addListener((observableValue, s, t1) -> infoBar.setLanguage(t1));

        TabController tabControl = new TabController(infoBar, myNavBar, myTurtleInfoBar);
        myRoot.setTop(tabControl.getTabController());
        return new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
    }

    /**
     * Called when the stylesheet is desired to be changed (i.e. when the combobox is changed).
     * Assumes non-null strings, since current calls to the method use a combobox with valid strings.
     * @param oldStyle String representing old style
     * @param newStyle String representing new style
     */
    private void updateStyle(String oldStyle, String newStyle){
        myRoot.getStylesheets().remove(oldStyle);
        myRoot.getStylesheets().add(newStyle);
    }

    /**
     * Called by the FrontendController to start up the scene and, eventually, show the stage with this scene.
     * Returns the actual Scene rather than a clone because cloning a Scene in Javafx is difficult.
     * This method would be unnecessary if the Controller classes were reorganized to eliminate the FrontendController.
     * @return Scene, overall Scene of the simulation
     */
    public Scene getScene(){
        return myScene;
    }

    /**
     * Called by the FrontendController to gain access to the Map of turtles.
     * This method would be unnecessary if the Controller classes were reorganized to eliminate the FrontendController.
     * @return Map of Turtle ID to turtle
     */
    public Map<Integer,Turtle> getTurtles() { return myTurtles; }

    /**
     * Called by the FrontendController to gain access to the CommandLine.
     * This method would be unnecessary if the Controller classes were reorganized to eliminate the FrontendController.
     * @return CommandLine object
     */
    public CommandLine getCommandLine() { return myCommandLine; }

}
