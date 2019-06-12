package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class SetHeading extends Command {
    private static final int PARAM_NUM = 1;


    public SetHeading() {
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: degrees (int). Turns turtle to an absolute heading given by degrees. Returns number of degrees moved.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        double oldOrientation = getValue(ORIENTATION);
        setValue(ORIENTATION, -myParameters.remove(0).execute());
        return Math.abs(getValue(ORIENTATION) - oldOrientation);
    }
}
