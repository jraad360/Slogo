package backend_internal.commands.boolean_operations;

import backend_internal.Command;

public class Equal extends Command {
    private static final int PARAM_NUM = 2;

    public Equal(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0.
     * @return - (double) result
     */
    @Override
    public double execute() {
        checkError();
        if(myParameters.get(0).execute() == myParameters.get(1).execute()){
            return 1;
        }
        return 0;
    }
}