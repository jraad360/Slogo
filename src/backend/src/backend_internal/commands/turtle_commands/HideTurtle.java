package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class HideTurtle extends Command {
    private static final int PARAM_NUM = 0;

    public HideTurtle(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Makes turtle invisible. Returns 0.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        setValue(SHOWING, 0.0);
        return 0;
    }
}
