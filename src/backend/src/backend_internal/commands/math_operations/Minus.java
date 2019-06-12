package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Minus extends Command {
    private static final int PARAM_NUM = 1;

    public Minus(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Minus = Parameters: expr (int). Returns negative of the values of expr.
     * @return
     */
    @Override
    public double execute() {
        return -1*myParameters.get(0).execute();
    }
}
