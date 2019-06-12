package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class Left extends Command {
    private static final int PARAM_NUM = 1;

    public Left() {
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Turns turtle counterclockwise by degrees angle. Returns the value of degrees.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        double oldOrientation = getValue(ORIENTATION);
        setValue(ORIENTATION, oldOrientation - myParameters.get(0).execute());
        return myParameters.get(0).execute();
    }
}
