package backend_internal.commands.complex_commands;

import backend_internal.*;

import java.util.Queue;
import java.util.Stack;

/**
 * Implements the logic for the For command. Extends command.
 * @author Jamie Palka
 */
public class For extends Command {

    private static final int PARAM_NUM = 2;
    private static final int RANGE_INDEX = 0;
    private static final int LIST_INDEX = 1;

    /**
     * Parameters: [variable start end increment] [command(s)] ([String int int int] [String(s)]). Runs command(s) for each value specified in the range, i.e., from (start - end), going by increment. Returns the value of the final command executed (or 0 if no commands are executed). Note, variable is assigned to each succeeding value so that it can be accessed by the command(s).
     * @return double - the reult of the command
     */
    @Override
    public double execute() {
        checkError();

        BracketList limitList = (BracketList)myParameters.get(RANGE_INDEX);
        String bracketString = limitList.getString().trim();
        String[] array = bracketString.split(" ");

        Variable variable = new Variable();
        variable.setParameter(array[0]);

        Constant start = new Constant();
        start.setParameter(array[1]);

        Constant end = new Constant();
        end.setParameter(array[2]);

        Constant increment = new Constant();
        increment.setParameter(array[3]);


        double result = 0;
        for(double k = start.execute(); k <= end.execute(); k += increment.execute()) {

            variable.setNumber(k);
            result =  myParameters.get(LIST_INDEX).execute();

        }
        return result;
    }

    public For() {
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

}
