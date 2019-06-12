package backend_internal;

import java.lang.reflect.Constructor;
import java.util.*;

// Property file-reading code from https://www.mkyong.com/java/java-properties-file-examples/

public class InputTypeFactory {
    public static final String PROPERTY_FILE = "class_locations";
    private Map<String, String> fileMap;

    /**
     * Creates instance of InputType Factory. Creates map of symbols that will be used to instantiate InputTypes by
     * reflection.
     */
    public InputTypeFactory(){
        createFileMap();
    }

    /**
     * Creates and returns an InputType based on the given user input. Called by Parser and any time we want to create
     * an object using reflection.
     * @param inputType - (String) specific object type to be created
     * @param info - (String) contains the user-typed into to be used when assigning values to InputType
     * @return - (InputType) InputType corresponding to user input
     * @throws IllegalArgumentException
     */
    public InputType createInputType(String inputType, String info) throws IllegalArgumentException {
        try{
//            System.out.println(inputType);
            Class inputClass = Class.forName(fileMap.get(inputType) + "." + inputType);
            Constructor inputConstructor = inputClass.getConstructor();
            InputType result = (InputType) inputConstructor.newInstance();

            result.setParameter(info);
            return result;

        } catch(Exception e){
            throw new IllegalArgumentException();
        }
    }

    private void createFileMap(){
        fileMap = new LinkedHashMap<>();
        var resources = ResourceBundle.getBundle(PROPERTY_FILE);
        for (var key : Collections.list(resources.getKeys())) {
            fileMap.put(key,resources.getString(key));
            }
    }
}
