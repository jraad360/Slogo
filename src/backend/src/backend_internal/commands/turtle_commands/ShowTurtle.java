package backend_internal.commands.turtle_commands;

import backend_internal.Command;

public class ShowTurtle extends Command {
    private static final int PARAM_NUM = 0;

    public ShowTurtle(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Makes turtle visible. Returns 1.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        setValue(SHOWING, 1.0);
        return 1;
    }
}
