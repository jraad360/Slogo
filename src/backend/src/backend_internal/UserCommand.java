package backend_internal;

import java.io.IOException;


/**
 * UserCommand extends Command and represents a command created by the user.
 * @author Jamie Palka and Jorge Raad
 */
public class UserCommand extends Command{
    private static final String USER_COMMANDS_FILE = "src/backend/src/resources/user_command_syntax.properties";
    private static final int PARAM_NUM = 0;

    public UserCommand(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    /**
     * Executes the contents of the user-defined command. Basically retrieves contents, assigns it a BracketList, and
     * executes the BracketList. This will have different effects depending on the specific command.
     * @return double - the result of the command function.
     */
    public double execute() {
        try {
            BracketList commands = new BracketList();
            String commandContent = PropertiesFileWriter.getPropertiesMap(USER_COMMANDS_FILE).get(myCommandName);
            commands.setParameter(commandContent.trim().split(",")[1]);
            return commands.execute();
        }
        catch(IOException e){
            throw new IllegalStateException("Given user command was not found.");
        }
    }

}
