package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Remainder extends Command {
    private static final int PARAM_NUM = 2;
    private static final int PARAM_1_INDEX = 0;
    private static final int PARAM_2_INDEX = 1;

    public Remainder(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returns remainder on dividing the value of expr1 by expr2.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return myParameters.get(PARAM_1_INDEX).execute() % myParameters.get(PARAM_2_INDEX).execute();
    }
}
