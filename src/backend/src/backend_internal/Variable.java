package backend_internal;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Variable represents a user-defined variable to be saved in a properties file.
 * @author Jamie Palka and Jorge Raad
 */
public class Variable implements InputType {
    private String myVariableName;
    private static final int PARAM_NUM = 0;
    private static final String VARIABLE_FILE = "src/backend/src/resources/variables.properties";

    public Variable() {

    }

    public Variable(String name) {
        myVariableName = name;
    }


    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    @Override
    public void setParameter(InputType name) { }

    /**
     * {@inheritDoc}
     * Sets the Variable's name to the given input.
     * @param parameter - desired variable name
     */
    @Override
    public void setParameter(String parameter) {
//        System.out.println(parameter);
        myVariableName = parameter;
    }

    /**
     * Updated the variable's value to the given input.
     * @param number - desired variable value
     */
    public void setNumber(Double number) {
        try {
            PropertiesFileWriter.addPropertiesToFile(myVariableName, number.toString(), VARIABLE_FILE);
        }
        catch(IOException e){
            throw new RuntimeException("File containing variables not found.");
        }
    }

    /**
     * {@inheritDoc}
     * Returns the value stored by the variable.
     * @return
     */
    @Override
    public double execute() {
        try {
            Map<String, String> variableList = PropertiesFileWriter.getPropertiesMap(VARIABLE_FILE);
            String value = variableList.get(myVariableName);
            if(value == null) {
                // if variable did not previously exist, then initialize to 0;
                PropertiesFileWriter.addPropertiesToFile(myVariableName, Double.toString(0), VARIABLE_FILE);
                return 0;
            }
            return Double.parseDouble(value);
        }
        catch(IOException e){
            return 0;
        }
    }
}
