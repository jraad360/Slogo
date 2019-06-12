package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Power extends Command {
    private static final int PARAM_NUM = 2;

    public Power(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: base exponent (int int). Returns base raised to the power of exponent.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return Math.pow(myParameters.get(0).execute(), myParameters.get(0).execute());
    }
}
