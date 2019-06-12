package backend_internal.commands.complex_commands;

import backend_internal.Command;

/**
 * Implements the logic for the IfElse command. Extends command.
 * @author Jamie Palka
 */
public class IfElse extends Command {
    // int for the number of times and list
    private static final int PARAM_NUM = 3;
    private static final int FIRST_PARAM = 0;
    private static final int SECOND_PARAM = 1;
    private static final int THIRD_PARAM = 2;

    public IfElse(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    /**
     * Parameters: expr [trueCommand(s)][falseCommand(s)] (int [String(s)][String(s)]). If expr is not 0, runs the trueCommands given in the first list, otherwise runs the falseCommands given in the second list.
     * @return - Returns the value of the final command executed (or 0 if no commands are executed).
     */
    @Override
    public double execute() {
        checkError();

        double result = 0;
        if(myParameters.get(FIRST_PARAM).execute() == 0) {

            result = myParameters.get(THIRD_PARAM).execute();
        }

        else {

            result = myParameters.get(SECOND_PARAM).execute();
        }

        return result;

    }

}
