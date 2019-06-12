package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class ClearScreen extends Command {
    private static final int PARAM_NUM = 0;

    public ClearScreen(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Erases turtle's trails and sends it to the home position. Returns the distance turtle moved.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        // TODO : HOW TO ERASE LINE???
        checkError();
        setValue(RESET_PATH, 1.0);
        double oldX = getValue(X);
        double oldY = getValue(Y);
        setValue(X, 0.0);
        setValue(Y, 0.0);
        setValue(ORIENTATION, 0.0);
        super.addPoint(0, 0);
        return Math.sqrt(((oldY)*(oldY) + (oldX)*(oldX)));
    }
}
