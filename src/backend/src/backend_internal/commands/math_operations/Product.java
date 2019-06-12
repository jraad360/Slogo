package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Product extends Command {
    private static final int PARAM_NUM = 2;
    private static final int PARAM_1_INDEX = 0;
    private static final int PARAM_2_INDEX = 1;

    public Product(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returns product of the values of expr1 and expr2.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return myParameters.get(PARAM_1_INDEX).execute()*myParameters.get(PARAM_2_INDEX).execute();
    }
}
