package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Difference extends Command {
    private static final int PARAM_NUM = 2;

    public Difference(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Difference = Parameters: expr1 expr2 (int int). Returns difference of the values of expr1 and expr2.
     * @return
     */
    @Override
    public double execute() {
        return myParameters.remove(0).execute() - myParameters.remove(0).execute();
    }
}
