package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class Forward extends Command {
    private static final int PARAM_NUM = 1;

    public Forward(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: pixels (int). Moves turtle forward in its current heading by pixels distance. Returns the value of pixels.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        double o = getValue(ORIENTATION);
        double newX = getValue(X) + (myParameters.get(0).execute())*Math.cos(o*Math.PI/180);
        double newY = getValue(Y) + (myParameters.get(0).execute())*Math.sin(o*Math.PI/180);
        setValue(X, newX);
        setValue(Y, newY);
        super.addPoint(getValue(X), getValue(Y));
        return myParameters.remove(0).execute();
    }
}
