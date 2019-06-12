package backend_external;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ExternalBackend {

    /**
     * Runs the given user input. This will be called with the user input entered into the frontend as the parameter.
     * The method will return the resulting double and will change any other necessary information, such as turtle
     * location used by the backend and the properties file containing the points of the turtle's path.
     * @param input
     * @return double resulting from running the input
     */
    double run(String input);

    /**
     * Changes the recognized language of commands to the requested language. This will be called from the frontend
     * when the user has selected a different language. The Parser will still retain access to user-defined commands and
     * universally used syntax.
     * @param language
     */
    void setLanguage(String language);
    
    /**
     * This updates the backend's version of the turtle data to the given information. The data in this map will be used
     * when executing the commands. This will be called before every time the run method is called so that any commands
     * carried out are done so with the latest information.
     * @param map
     */
    void updateTurtleData(List<Map<String, Double>> map);

    /**
     * Returns updated list containing the turtles' information. This will be the list passed in through updateTurtleData
     * but with some data changed as a result of the commands run. This will be called by the backend in order to set
     * its version of the turtle data to these updated properties.
     * @return - updated list of maps containing all the turtles' information
     */
    List<Map<String, Double>> getTurtleInfo();

    /**
     * Returns a map containing all currently stored variables. The key is a String representing the variable name and the
     * value is a String representing the variable's number value. This is used by the frontend solely to display to the
     * user.
     * @return - map of variables
     */
    Map<String, String> getVariables();

    /**
     * Returns a map containing all user commands along with their contents. The key is a String representing the
     * command name and the value is a String containing the command's contents. This is used by the frontend solely to
     * display to the user.
     * @return - map of user commands and their contents
     */
    Map<String, String> getUserCommands();

    /**
     * Returns a map containing all original commands along with their descriptions. The key is a String representing
     * the command name and the value is a String containing the command's description. This is used by the frontend
     * solely to display to the user.
     * @return - map of original commands and their descriptions
     */
    Map<String, String> getOriginalCommands();

    /**
     * Returns a List of Lists of Doubles. Each List within the List corresponds to a turtle. Within each of these inner
     * lists, there are doubles. Each even-indexed double represents an x value and each following odd-indexed double is
     * its corresponding y value (so this list may only contain an even number of entries). This information is used by
     * the front end to add points and lines connecting them to the screen. There are Lists of Doubles for each turtle
     * because each turtle has its own trail.
     * @return - list of lists of doubles containing info on points to add to turtles' trails
     */
    List<ArrayList<Double>> getPoints();

    List<Double> getStamps();
}
