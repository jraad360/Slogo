package backend_internal.commands.turtle_queries;

import backend_internal.Command;

public class XCoordinate extends Command {
    private static final int PARAM_NUM = 0;

    public XCoordinate(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * XCoordinate = Parameters: none. Returns the turtle's X coordinate from the center of the screen.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        return getValue(X);
    }
}
