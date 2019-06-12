package backend_internal.commands.complex_commands;

import backend_internal.BracketList;
import backend_internal.Command;
import backend_internal.PropertiesFileWriter;
import backend_internal.Word;

import java.io.IOException;
import java.util.Map;

/**
 * Implements the logic for the IfElse command. Extends command.
 * @author Jamie Palka and Jorge Raad.
 */
public class MakeUserInstruction extends Command {
    private static final String USER_COMMANDS_FILE = "src/backend/src/resources/user_command_syntax.properties";
    private static final String USER_COMMANDS_REGEX = "src/backend/src/resources/user_command_regex.properties";

    private static final int PARAM_NUM = 3;
    private static final int NAME_INDEX = 0;
    private static final int VAR_LIST_INDEX = 1;
    private static final int COMMAND_LIST_INDEX = 2;

    public MakeUserInstruction(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: commandName [variable(s)][command(s)] (String [String(s)][String(s)]). Assigns command(s) given in the second list to commandName using parameters given in first list as variables. When commandName is used later in a program, any given values are assigned to variables that can be accessed when the command list is run and the value of the final command executed is returned (or 0 if no commands are executed).
     * @return - Returns 1 if command is successfully defined, otherwise 0.
     */
    @Override
    public double execute() {
        checkError();
        // TODO : if we can restructure BracketList and ask directly for parameters as InputTypes instead of Strings, we can avoid casting here
        // There's really no need for this variables argument.
        // String varString = ((BracketList) myParameters.get(VAR_LIST_INDEX)).getString();
        // TODO : avoid casting here
        try {
            String commandName = ((Word) myParameters.get(NAME_INDEX)).getString();
            String variables = ((BracketList)myParameters.get(VAR_LIST_INDEX)).getString();
            String commandString = ((BracketList) myParameters.get(COMMAND_LIST_INDEX)).getString();
            PropertiesFileWriter.addPropertiesToFile(commandName, variables + "," + commandString, USER_COMMANDS_FILE);
            Map<String, String> map = PropertiesFileWriter.getPropertiesMap(USER_COMMANDS_REGEX);
            PropertiesFileWriter.addPropertiesToFile("UserCommand", map.get("UserCommand") + "|" + commandName, USER_COMMANDS_REGEX);
            // always update the symbols to account for any changes in user commands
            myParser.replacePatterns(myParser.CURRENT_LANGUAGE);
        }
        catch(ClassCastException | IOException e){
            System.out.println("'To' command does not take either " + myParameters.get(NAME_INDEX) +
                    "or " + myParameters.get(VAR_LIST_INDEX)+ " as an input.");
            System.out.println("User-defined command properties file missing.");
            return 0;
        }
        return 1;
    }

    @Override
    protected void checkError() throws IllegalArgumentException {
        super.checkError();
        // TODO : check if valid command
    }
}
