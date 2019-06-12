package backend_internal.commands.complex_commands;

import backend_internal.Command;

/**
 * Implements the logic for the If command. Extends command.
 * @author Jamie Palka
 */
public class If extends Command {

    // int for the number of times and list
    private static final int PARAM_NUM = 2;
    private static final int FIRST_PARAM = 0;
    private static final int SECOND_PARAM = 1;

    public If(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    /**
     * Parameters: expr [command(s)] (int [String(s)]). If expr is not 0, runs the command(s) given in the list. Returns the value of the final command executed (or 0 if no commands are executed).
     * @return double - the result of the command.
     */
    @Override
    public double execute() {
        checkError();

        double result = 0;
        if(myParameters.get(FIRST_PARAM).execute() != 0) {

            result = myParameters.get(SECOND_PARAM).execute();
        }

        return result;

    }
}
