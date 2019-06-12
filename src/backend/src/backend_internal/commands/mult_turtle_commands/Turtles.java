package backend_internal.commands.mult_turtle_commands;

import backend_internal.Command;

public class Turtles extends Command {
    private static final int PARAM_NUM = 0;

    public Turtles(){
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns number of turtles created so far.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        return LIST_OF_TURTLES.size();
    }

}
