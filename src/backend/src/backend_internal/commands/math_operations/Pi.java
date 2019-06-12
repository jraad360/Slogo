package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Pi extends Command {
    private static final int PARAM_NUM = 0;

    public Pi(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Reports the number of pi.
     * @return
     */
    @Override
    public double execute() {
        return Math.PI;
    }
}
