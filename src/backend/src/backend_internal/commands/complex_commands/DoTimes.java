package backend_internal.commands.complex_commands;

import backend_internal.*;

import java.util.Queue;
import java.util.Stack;

/**
 * Implements the logic for the DoTimes command. Extends command.
 * @author Jamie Palka
 */
public class DoTimes extends Command {

    private static final int LIMIT_INDEX = 0;
    private static final int LIST_INDEX = 1;
    private static final int PARAM_NUM = 2;

    public DoTimes() {
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: [variable limit] [command(s)] ([String int] [String(s)]). Runs command(s) for each value specified in the range, i.e., from (1 - limit) inclusive. Returns the value of the final command executed (or 0 if no commands are executed). Note, variable is assigned to each succeeding value so that it can be accessed by the command(s).
     * @return double - the result of the command.
     */
    @Override
    public double execute() {
        checkError();

        BracketList limitList = (BracketList)myParameters.get(LIMIT_INDEX);
        String bracketString = limitList.getString().trim();
        String[] array = bracketString.split(" ");
        Variable variable = new Variable();
        variable.setParameter(array[0]);
        Constant limit = new Constant();
        limit.setParameter(array[1]);

        double result = 0;
        for(int k = 1; k <= limit.execute(); k++) {

            variable.setNumber((double)k);
            result =  myParameters.get(LIST_INDEX).execute();
        }

        return result;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

}
