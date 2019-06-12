package backend_internal.commands.math_operations;

import backend_internal.Command;

public class Cosine extends Command {
    private static final int PARAM_NUM = 1;

    public Cosine(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Returns cosine of degrees.
     * @return
     */
    @Override
    public double execute() {
        return Command.round(Math.cos(Math.PI/180*myParameters.get(0).execute()));
    }
}
