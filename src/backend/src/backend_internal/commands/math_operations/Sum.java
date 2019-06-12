package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Sum extends Command {
    private static final int PARAM_NUM = 2;

    public Sum(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returnsnteg sum of the values of expr1 and expr2.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return myParameters.remove(0).execute() + myParameters.remove(0).execute();
    }
}
