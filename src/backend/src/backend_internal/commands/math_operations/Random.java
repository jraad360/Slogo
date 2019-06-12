package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Random extends Command {
    private static final int PARAM_NUM = 1;

    public Random(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: max (int). Returns random non-negative number strictly less than max.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return myParameters.get(0).execute()*Math.random();
    }
}
