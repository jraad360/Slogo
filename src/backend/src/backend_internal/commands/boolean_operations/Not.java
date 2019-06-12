package backend_internal.commands.boolean_operations;

import backend_internal.Command;

public class Not extends Command {
    private static final int PARAM_NUM = 1;

    public Not(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: test (int). Returns 1 if test is 0 and 0 if test is non-zero.
     * @return - (double) result
     */
    @Override
    public double execute() {
        checkError();
        if(myParameters.get(0).execute() == 0){
            return 1;
        }
        return 0;
    }
}