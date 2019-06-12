package backend_internal.commands.boolean_operations;

import backend_internal.Command;

public class LessThan extends Command {
    private static final int PARAM_NUM = 2;

    public LessThan(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0.
     * @return - (double) result
     */
    @Override
    public double execute() {
        checkError();
        double diff = myParameters.get(0).execute() - myParameters.get(1).execute();
        if(diff < 0){
            return 1;
        }
        return 0;
    }
}
