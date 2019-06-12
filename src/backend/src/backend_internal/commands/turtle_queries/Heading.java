package backend_internal.commands.turtle_queries;

import backend_internal.Command;

public class Heading extends Command {
    private static final int PARAM_NUM = 0;

    public Heading(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns the turtle's heading in degrees.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        return getValue(ORIENTATION);
    }
}
