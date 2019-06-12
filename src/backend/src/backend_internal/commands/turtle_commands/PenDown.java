package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class PenDown extends Command {
    private static final int PARAM_NUM = 0;

    public PenDown(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Puts pen down such that when the turtle moves, it leaves a trail. Returns 1.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        setValue(PEN_DOWN, 1.0);
        return 1;
    }
}
