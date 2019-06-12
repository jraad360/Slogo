package frontend_internal;

import frontend_external.ErrorHandler;
import frontend_external.Turtle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * This class provides a place for users to interact with the different display options of the program.
 * Users are given the option to change the sprite image, pen color, background color, and language used.
 * Additionally, there is a button that will pull up a display of the recent commands run in the program.
 * This class must have access to the current list of Turtles in order to change their image and pen color.
 * It must also have access to the command history object in order to pull it up as a display when the button is clicked.
 * This class also reads values in from different files displaying the different image, color, and language options.
 * It assumes that these files are kept up to date and that all the necessary resources are provided within the resources folder.
 *
 * @author Mary Stuart Elder and Eric Werbel
 */
public class NavBar extends Bar{

    public static final String HISTORY_WINDOW_TITLE = "Command History";
    public static final String STYLE_FILE_ENDING = ".css";
    public static final Map<String, Color> COLOR_MAP = Map.of(
            "Black", Color.BLACK,
            "Red", Color.RED,
            "Blue", Color.BLUE,
            "Green", Color.GREEN,
            "White", Color.WHITE
    );

    private CommandHistory myHistory;
    private Map<Integer, Turtle> myTurtles;
    protected SimpleStringProperty myStyle = new SimpleStringProperty();
    protected SimpleStringProperty myLanguage = new SimpleStringProperty();

    /**
     * Initializes the NavBar object to be displayed at the top of the screen.
     * Contains functionality for changing turtle image, pen color, background color, and language used.
     * Also has option to view history of commands recently run in the environment.
     *
     * @param history = CommandHistory object containing information about the recent commands run
     * @param turtles = map of current Turtles controlled by the program
     */
    public NavBar(CommandHistory history, Map<Integer,Turtle> turtles) {
        myHistory = history;
        myTurtles = turtles;
        myBar = (HBox) makeDisplay();
        myStyle.setValue(SlogoView.DEFAULT_STYLE);
    }

    /**
     * Creates the combo box that allows users to switch the sprite image.
     *
     * @return a combo box with options of different sprite images
     */
    private ComboBox makeImageBox() {
        ComboBox imageBox =  createComboBox("src/frontend/data/SpriteImages.txt");
        imageBox.valueProperty().addListener((ChangeListener<String>) (observableValue, oldVal, newVal) -> updateTurtleImage(newVal));
        return imageBox;
    }

    /**
     * This method is called when the listener in the combo box is alerted to a change to its value.
     * Takes the string in the combo box and converts to a file name corresponding to the new image of the sprite.
     *
     * @param newImageName = new string value selected in the combo box
     */
    private void updateTurtleImage(String newImageName){
        if (newImageName.length() > 0) {
            String imageName = newImageName + ".png";
            for (Turtle t : myTurtles.values()) {
                if (t.isActive() == 1) {
                    t.setImage(imageName);
                }
            }
        }
    }

    /**
     * Creates the combo box that allows users to switch the pen color.
     *
     * @return a combo box with options of different pen colors
     */
    private ComboBox makePenColorBox() {
        ComboBox penColorBox = createComboBox("src/frontend/data/PenColor.txt");
        penColorBox.valueProperty().addListener((ChangeListener<String>) (observableValue, oldVal, newVal) -> updatePenColor(newVal));
        return penColorBox;
    }

    /**
     * This method is called when the listener in the combo box is alerted to a change to its value.
     * Takes the string in the combo box and uses it as a key in a map to find the new color.
     *
     * @param newColor = new string value selected in the combo box
     */
    private void updatePenColor(String newColor){
        for (Turtle t : myTurtles.values()) {
            if (t.isActive() == 1) {
                t.getPen().setColor(COLOR_MAP.get(newColor));
            }
        }
    }

    /**
     * Creates the combo box that allows users to switch the background color.
     *
     * @return a combo box with options of different background colors
     */
    private ComboBox makeBGColorBox() {
        ComboBox colorBox = createComboBox("src/frontend/data/BGColors.txt");
        colorBox.valueProperty().addListener((ChangeListener<String>) (observableValue, oldVal, newVal) -> myStyle.setValue(newVal + STYLE_FILE_ENDING));
        return colorBox;
    }

    /**
     * Creates the combo box that allows users to switch the language being used.
     *
     * @return a combo box with options of different languages to use
     */
    private ComboBox makeLanguageBox(){
        ComboBox langBox = createComboBox("src/frontend/data/languages.txt");
        langBox.valueProperty().addListener((ChangeListener<String>) (observableValue, oldVal, newVal) -> myLanguage.setValue(newVal));
        return langBox;
    }

    /**
     * Creates the list of combo box options by reading in the different values from text files.
     *
     * @param fileName = gives the correct file name containing the options for the given combo box
     * @return a combo box containing the correct list of options
     */
    private ComboBox createComboBox(String fileName) {
        ComboBox myBox = new ComboBox();
        try {
            BufferedReader br = createBR(fileName);
            String nextItem;
            while ((nextItem = br.readLine()) != null) {
                myBox.getItems().add(nextItem);
            }
        }
        catch (IOException e) {
            new ErrorHandler("fileError");
        }
        return myBox;
    }

    /**
     * Creates the BufferedReader that reads the files to get the options for each combo box.
     *
     * @param fileName = name of the file to be read from by the reader
     * @return a BufferedReader that will read from the given file
     * @throws FileNotFoundException in case the text file cannot be found
     */
    private BufferedReader createBR(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        return  new BufferedReader(new FileReader(file));
    }

    // New stage code based on: https://o7planning.org/en/11533/opening-a-new-window-in-javafx

    /**
     * Creates a new stage to display the recent history of commands run in the environment
     */
    private void showHistory(){

        ScrollPane scrollHistory = new ScrollPane();
        VBox historyDisplay;
        historyDisplay = myHistory.getHistoryDisplay();

        scrollHistory.setContent(historyDisplay);
        scrollHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollHistory.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene historyScene = new Scene(scrollHistory, 400, 400);
        Stage historyWindow = new Stage();
        historyWindow.setTitle(HISTORY_WINDOW_TITLE);
        historyWindow.setScene(historyScene);
        historyWindow.show();
    }

    /**
     * Creates the actual display that is shown to the user.
     * Combines all different aspects of the display into one region that is displayed.
     *
     * @return a region that contains all information for this portion of the frontend to be displayed
     */
    @Override
    protected Region makeDisplay() {
        var navBar = new HBox(10);
        Button historyButton = makeButton("History", event -> showHistory());
        ComboBox imageList = makeImageBox();
        ComboBox penColors = makePenColorBox();
        ComboBox bgColors = makeBGColorBox();
        ComboBox languages = makeLanguageBox();
        navBar.getChildren().addAll(historyButton, createSpacer(), new Label("Sprite Image:"), imageList, createSpacer(), new Label("Pen Color:"), penColors, createSpacer(), new Label("Background Color:"), bgColors, createSpacer(), new Label("Language: "), languages);
        return navBar;
    }
}
