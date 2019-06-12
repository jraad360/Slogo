package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Tangent extends Command {
    private static final int PARAM_NUM = 1;

    public Tangent(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Returns tangent of degrees.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return Command.round(Math.tan(Math.PI/180*myParameters.get(0).execute()));
    }

    @Override
    protected void checkError() throws IllegalArgumentException {
        super.checkError();
        // TODO : check for pi/2, pi/4 ... ?
    }
}
