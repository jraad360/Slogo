package backend_internal.commands.complex_commands;

import backend_internal.*;

/**
 * Implements the logic for the IfElse command. Extends command.
 * @author Jamie Palka
 */
public class Repeat extends Command {

    // int for the number of times and list
    private static final int PARAM_NUM = 2;
    private static final int FIRST_PARAM = 0;
    private static final int SECOND_PARAM = 1;

    public Repeat(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    /**
     * Parameters: expr [command(s)] (int [String(s)]). Runs command(s) given in the list the value of expr number of times.  Note, the value of the current iteration, starting at 1, is automatically assigned to the variable :repcount so that it can be accessed by the command(s).
     * @return - Returns the value of the final command executed (or 0 if no commands are executed).
     */
    @Override
    public double execute() {
        checkError();

       // assuming parameters are put into list in the order they appear, this will execute the list the correct number of times.
        InputType myBracketList = myParameters.get(SECOND_PARAM);

        double result = 0;
        for(int i = 1; i <= myParameters.get(FIRST_PARAM).execute(); i++) {

                result = myBracketList.execute();

            }
        return result;

        }

    }