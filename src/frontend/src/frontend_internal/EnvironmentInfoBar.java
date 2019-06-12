package frontend_internal;

import backend_external.ExternalBackend;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

public class EnvironmentInfoBar extends ScrollPaneBar{

    /**
     * This class offers a display (HBox) of the built-in functions, variables, and user defined functions.
     * The interface for the external backend is passed in to allow this class to update the display as changes occur.
     * Assumes the backend will update and modify the data structures for the function and variable info so the frontend listeners will trigger updates.
     * @author Mary Stuart Elder
     */

    private ScrollPane myFunctionDisplay;
    private ScrollPane myVariableDisplay;
    private ScrollPane myUserDefinedDisplay;
    private ExternalBackend myBackend;
    private TextField myCommandLine;
    private ObservableMap<String, String> myVariablesMap;
    private ObservableMap<String, String> myUserCommandsMap;

    // TODO: move constants into properties file
    public static final int PANE_HEIGHT = 50;
    public static final int BUILT_IN_WIDTH = 300;
    public static final int VARIABLE_WIDTH = 200;
    public static final int USER_DEFINED_WIDTH = 300;
    public static final int COMMAND_DISPLAY_V_SPACING = 5;
    public static final int COMMAND_DISPLAY_H_SPACING = 5;
    public static final double BAR_WIDTH = 600.0;
    public static final double BAR_HEIGHT = 100.0;
    public static final String COMMANDS_TITLE = "Available Commands";
    public static final String VARIABLE_TITLE = "Available Variables";
    public static final String USER_DEFINED_TITLE = "Available User Functions";
    public static final String USE_COMMAND_BUTTON_TEXT = "Use Command";
    public static final String HOVER_HELP_DESCRIPTION = "Hover over command for more info";
    public static final String SUBTITLE_ID = "subtitle";
    public static final String LABEL_END = ": ";

    /**
     * This Constructor takes in a CommandLine object and an interface for the external backend.
     * On construction, the instance variables are updated from the CommandLine information and the display is initialized.
     * @param commandLine CommandLine from SlogoView
     * @param backend interface of the external backend, used to update display
     */
    public EnvironmentInfoBar(CommandLine commandLine, ExternalBackend backend){
        myCommandLine = commandLine.getTextField();

        myVariablesMap = commandLine.myVariablesMap;
        myVariablesMap.addListener((MapChangeListener<String, String>) change -> updateDisplay(myVariablesMap, myVariableDisplay, VARIABLE_TITLE, VARIABLE_WIDTH));

        myUserCommandsMap = commandLine.myUserCommandsMap;
        myUserCommandsMap.addListener((MapChangeListener<String, String>) change -> updateDisplay(myUserCommandsMap, myUserDefinedDisplay, USER_DEFINED_TITLE, USER_DEFINED_WIDTH));

        myBackend = backend;
        myBar = (HBox) makeDisplay();
        myFunctionDisplay.setContent(buildFunctionsDisplay());
    }


    /**
     * Called from SlogoView/controlling class when language ComboBox is changed
     * Assumes this will only be called by the SlogoView class (from ComboBox) and therefore, bad input isn't possible
     * Sets the language of the simulation from display perspective
     * @param language string representing the properties file to reference
     */
    public void setLanguage(String language){
        myBackend.setLanguage(language);
        myFunctionDisplay.setContent(buildFunctionsDisplay());
    }

    /**
     * Generic method called to update the display of a certain region based on the current state.
     * @param displayMap Map containing information to be stored in the display
     * @param display ScrollPane currently storing the display
     * @param title String, title of the display
     * @param displayWidth int, width of the display
     */
    private void updateDisplay(Map<String, String> displayMap, ScrollPane display, String title, int displayWidth){
        if(displayMap == null || displayMap.isEmpty()){
            display = makeScrollPane(title, displayWidth, PANE_HEIGHT);
        }
        else{
            display.setContent(buildUpdatedDisplay(displayMap, title));
        }
    }

    /**
     * Called as the simulation updates to build the new, updated display.
     * Used to set the contents of the ScrollPane.
     * @param map, Map containing information to be stored in the display
     * @param displayTitle String, title of the display
     * @return VBox, the updated display contents
     */
    private VBox buildUpdatedDisplay(Map<String, String> map, String displayTitle){
        VBox box = new VBox(COMMAND_DISPLAY_V_SPACING);
        box.getChildren().add(new Text(displayTitle));
        for(Map.Entry<String, String> entry: map.entrySet()){
            HBox line = new HBox(COMMAND_DISPLAY_H_SPACING);
            Label key = new Label(entry.getKey() + LABEL_END);
            Label value = new Label(entry.getValue());
            line.getChildren().addAll(key, value);
            box.getChildren().add(line);
        }
        return box;
    }

    /**
     * Used to build the initialized functions display.
     * Uses original commands map from the backend to build the region (assumes valid map).
     * @return VBox, functions display
     */
    private VBox buildFunctionsDisplay(){
        VBox functionsDisplay = new VBox(COMMAND_DISPLAY_V_SPACING);
        functionsDisplay.getChildren().add(new Text(COMMANDS_TITLE));

        Text helpText = new Text(HOVER_HELP_DESCRIPTION);
        helpText.setId(SUBTITLE_ID);
        functionsDisplay.getChildren().add(helpText);

        for(Map.Entry<String, String> commandEntry: myBackend.getOriginalCommands().entrySet()){
            HBox optionHBox = new HBox(COMMAND_DISPLAY_H_SPACING);
            Label commandLabel = new Label(commandEntry.getKey());
            Button useButton = makeButton(USE_COMMAND_BUTTON_TEXT, event -> addCommandToTextField(commandEntry.getKey()));
            setHelpTooltip(commandEntry.getValue(), commandLabel);
            optionHBox.getChildren().addAll(commandLabel, useButton);
            functionsDisplay.getChildren().add(optionHBox);
        }
        return functionsDisplay;
    }

    /**
     * Sets a tooltip with a given text content to appear over scrollover on toolTipControl.
     * @param helpText Contents of tooltip
     * @param toolTipControl Control object to set the tooltip on
     */
    private void setHelpTooltip(String helpText, Control toolTipControl){
        Tooltip commandTooltip = new Tooltip();
        commandTooltip.setText(helpText);
        commandTooltip.setPrefWidth(BUILT_IN_WIDTH + VARIABLE_WIDTH);
        commandTooltip.setWrapText(true);
        toolTipControl.setTooltip(commandTooltip);
    }

    /**
     * Sets value of TextField in CommandLine to contents of command.
     * @param command String, command to add
     */
    private void addCommandToTextField(String command){
        myCommandLine.setText(command);
    }

    /**
     * Creates the actual display that is shown to the user.
     * Combines all different aspects of the display into one region that is displayed.
     *
     * @return a region that contains all information for this portion of the frontend to be displayed
     */
    @Override
    protected Region makeDisplay() {
        myFunctionDisplay = makeScrollPane(COMMANDS_TITLE, BUILT_IN_WIDTH, PANE_HEIGHT);
        myVariableDisplay = makeScrollPane(VARIABLE_TITLE, VARIABLE_WIDTH, PANE_HEIGHT);
        myUserDefinedDisplay = makeScrollPane(USER_DEFINED_TITLE, USER_DEFINED_WIDTH, PANE_HEIGHT);
        HBox sideBar = new HBox();
        sideBar.getChildren().addAll(createSpacer(), myFunctionDisplay,createSpacer(), myVariableDisplay, createSpacer(), myUserDefinedDisplay, createSpacer());
        sideBar.setPrefSize(BAR_WIDTH, BAR_HEIGHT);
        return sideBar;
    }
}
