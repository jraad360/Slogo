package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class SetPosition extends Command {
    private static final int PARAM_NUM = 2;
    private static final int X_INDEX = 0;
    private static final int Y_INDEX = 1;

    public SetPosition(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: x y (int int). Moves turtle to an absolute screen position, where (0, 0) is the center of the screen. Returns the distance turtle moved.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        double oldX = getValue(X);
        double oldY = getValue(Y);
        double newX = myParameters.get(X_INDEX).execute();
        double newY = myParameters.get(Y_INDEX).execute();
        setValue(X, newX);
        setValue(Y, newY);
        super.addPoint(newX, newY);
        return Math.sqrt(((oldY-newY)*(oldY-newY) + (oldX-newX)*(oldX-newX)));
    }
}
