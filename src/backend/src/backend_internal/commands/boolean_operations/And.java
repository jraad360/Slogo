package backend_internal.commands.boolean_operations;

import backend_internal.Command;

public class And extends Command {
    private static final int PARAM_NUM = 2;

    public And(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: test1 test2 (int int). Returns 1 if test1 and test2 are non-zero, otherwise 0.
     * @return - (double) result
     */
    @Override
    public double execute() {
        checkError();
        if(myParameters.get(0).execute() != 0 && myParameters.get(1).execute() != 0){
            return 1;
        }
        return 0;
    }
}