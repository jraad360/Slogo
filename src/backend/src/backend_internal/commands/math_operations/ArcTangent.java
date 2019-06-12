package backend_internal.commands.math_operations;

import backend_internal.Command;

public class ArcTangent extends Command {
    private static final int PARAM_NUM = 1;

    public ArcTangent(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    /**
     * Parameters: degrees (int). Returns arctangent of degrees.
     */
    public double execute() {
        checkError();
        return Command.round(180/Math.PI*Math.atan(myParameters.get(0).execute()));
    }
}