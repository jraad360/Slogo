package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Quotient extends Command {
    private static final int PARAM_NUM = 2;
    private static final int PARAM_1_INDEX = 0;
    private static final int PARAM_2_INDEX = 1;

    public Quotient(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr1 expr2 (int int). Returns quotient of the values of expr1 and expr2.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return myParameters.get(PARAM_1_INDEX).execute()/myParameters.get(PARAM_2_INDEX).execute();
    }

    @Override
    protected void checkError() throws IllegalArgumentException {
        super.checkError();
        if(myParameters.get(PARAM_2_INDEX).execute() == 0){
            throw new IllegalArgumentException("Cannot divide by 0.");
        }
    }
}
