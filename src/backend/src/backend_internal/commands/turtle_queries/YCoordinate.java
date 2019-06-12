package backend_internal.commands.turtle_queries;

import backend_internal.Command;

public class YCoordinate extends Command {
    private static final int PARAM_NUM = 0;

    public YCoordinate(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns the turtle's Y coordinate from the center of the screen.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        return getValue(Y);
    }
}
