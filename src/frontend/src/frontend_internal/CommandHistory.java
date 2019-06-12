package frontend_internal;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    /**
     * This class is used as an interface for the simulation history.
     * The myHistory variable originally referenced a file that was written to for logging the history.
     * This was later changed to a list of strings for greater flexibility and an easy path to expansion for multiple simulations.
     * Takes in the TextField representing the command line and, when a new command is entered, updates the history.
     * Assumes a valid TextField input
     * @author Mary Stuart Elder and Eric Werbel
     */

    private List<String> myHistory = new ArrayList<>();

    public static final String MOST_RECENT_LABEL = "Most Recent";
    public static final String LEAST_RECENT_LABEL = "Least Recent";
    public static final int HISTORY_VERTICAL_SPACING = 8;
    public static final int HISTORY_HORIZONTAL_SPACING = 15;
    public static final String NEW_LINE = System.lineSeparator();   // %n did not work, idea from https://stackoverflow.com/questions/19084352/how-to-write-new-line-character-to-a-file-in-java

    /**
     * Takes in the TextField from the command line, stores its contents in the history, and clears the TextField.
     * Called from the CommandLine when the "Execute" button is pressed to store and clear the TextField.
     * Assumes a valid TextField is passed in.
     * @param txt command line text field
     */
    public void storeAndClearTextField(TextField txt) {
        var userInput = txt.getText();
        appendInputToHistory(userInput + NEW_LINE);
        txt.clear();
    }

    /**
     * Called from storeAndClearTextField to add string to history.
     * History currently a list of strings, could be a different Object though.
     * @param str String to be added to the history
     */
    private void appendInputToHistory(String str) {
        myHistory.add(str);
    }

    /**
     * Builds a VBox with a designed layout to represent the history.
     * Called when the History button is pressed to build the history display.
     * @return VBox display of history
     */
    private VBox buildHistoryDisplay() {
        VBox historyDisplay = new VBox(HISTORY_VERTICAL_SPACING);
        Label leastRecent = new Label(LEAST_RECENT_LABEL);
        historyDisplay.getChildren().add(leastRecent);

        for(int i=0; i<myHistory.size();i++){
            HBox currentLine = makeLineInDisplay(myHistory.get(i), i+1);
            historyDisplay.getChildren().add(currentLine);
        }

        Label mostRecent = new Label(MOST_RECENT_LABEL);
        historyDisplay.getChildren().add(mostRecent);

        return historyDisplay;
    }

    /**
     * Called from buildHistoryDisplay to create a line in the history VBox.
     * Assumes valid string for content, but null strings should still display without error.
     * @param content String representing the content of the line
     * @param lineNumber int representing the line in the history
     * @return HBox of the line in the display
     */
    private HBox makeLineInDisplay(String content, int lineNumber){
        HBox currentLine = new HBox(HISTORY_HORIZONTAL_SPACING);
        Label currentNumber = new Label(Integer.toString(lineNumber));
        Text currentUserText = new Text(content);
        currentLine.getChildren().addAll(currentNumber, currentUserText);
        return currentLine;
    }

    /**
     * Called from external classes to get the visual representation of the History.
     * @return VBox, representation of history display
     */
    public VBox getHistoryDisplay() {
        return buildHistoryDisplay();
    }

}
