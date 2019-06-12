package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class Right extends Command {
    private static final int PARAM_NUM = 1;

    public Right(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Turns turtle clockwise by degrees angle. Returns the value of degrees.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        double oldOrientation = getValue(ORIENTATION);
        setValue(ORIENTATION, oldOrientation + myParameters.get(0).execute());
        return myParameters.get(0).execute();
    }
}
