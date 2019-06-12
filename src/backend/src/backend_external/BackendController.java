package backend_external;

import backend_internal.Command;
import backend_internal.CommandRunner;
import backend_internal.PropertiesFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BackendController implements ExternalBackend {
    public static final String VARIABLES_FILE = "src/backend/src/resources/variables.properties";
    public static final String SIMPLE_COMMANDS_FOLDER = "src/backend/src/resources/reference";
    public static final String USER_COMMANDS_FILE = "src/backend/src/resources/user_command_syntax.properties";
    private CommandRunner myCR;
    private List<Map<String, Double>> myTurtleInfo;
    private List<Double> myStamps;

    public BackendController(){
        myCR = new CommandRunner();
        myTurtleInfo = new ArrayList<>();
        myStamps = new ArrayList<>();
    }


    @Override
    public double run(String command) {
        Command.POINTS = new ArrayList<>();
        for(int k = 0; k < myTurtleInfo.size(); k++){
            Command.POINTS.add(new ArrayList<>());
        }

        double ans = 0;
        for(Map<String, Double> turtle : myTurtleInfo) {
            Command.CURRENT_TURTLE_MAP = turtle;
            if(command.toLowerCase().contains(" ask") ||
                    command.toLowerCase().contains("tell")) {
                ans = myCR.parseToExecute(command);
                break;
            }
            else if(turtle.get("active") != 0){
                ans = myCR.parseToExecute(command);
            }
        }
        return ans;
    }


    @Override
    public void setLanguage(String language) {
        myCR.setLanguage(language);
    }


    @Override
    public void updateTurtleData(List<Map<String, Double>> list){
        myTurtleInfo = list;
        Command.LIST_OF_TURTLES = list;
        Command.STAMPS = myStamps;
    }


    @Override
    public List<Map<String, Double>> getTurtleInfo() {
        return myTurtleInfo;
    }

    @Override
    public List<Double> getStamps() {
        return myStamps;
    }


    @Override
    public Map<String, String> getVariables() {
        try{
            return PropertiesFileWriter.getPropertiesMap(VARIABLES_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    @Override
    public Map<String, String> getUserCommands() {
        try{
            return PropertiesFileWriter.getPropertiesMap(USER_COMMANDS_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // https://stackoverflow.com/questions/4917326/how-to-iterate-over-the-files-of-a-certain-directory-in-java

    @Override
    public Map<String, String> getOriginalCommands() {
        Map<String, String> myOriginalCommands = new LinkedHashMap<>();
        File dir = new File(SIMPLE_COMMANDS_FOLDER);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                myOriginalCommands.put(child.getName(), PropertiesFileWriter.readFileToString(child));
            }
        }
        return myOriginalCommands;
    }

    @Override
    public List<ArrayList<Double>> getPoints(){
        return Command.POINTS;
    }

}
