package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Sine extends Command {
    private static final int PARAM_NUM = 1;

    public Sine(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Returns sine of degrees.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return Command.round(Math.sin(Math.PI/180*myParameters.get(0).execute()));
    }
}
