package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class Home extends Command {
    private static final int PARAM_NUM = 0;

    public Home(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Moves turtle to the center of the screen (0 0). Returns the distance turtle moved.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        double oldX = getValue(X);
        double oldY = getValue(Y);
        setValue(X, 0.0);
        setValue(Y, 0.0);
        super.addPoint(0, 0);
        return Math.sqrt(((oldY)*(oldY) + (oldX)*(oldX)));
    }
}
