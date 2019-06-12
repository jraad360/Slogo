package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class PenUp extends Command {
    private static final int PARAM_NUM = 0;

    public PenUp(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Puts pen up such that when the turtle moves, it does not leave a trail. Returns 0.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        setValue(PEN_DOWN, 0.0);
        return 0;
    }
}
