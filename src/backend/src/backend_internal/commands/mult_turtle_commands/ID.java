package backend_internal.commands.mult_turtle_commands;

import backend_internal.Command;

public class ID extends Command {
    private static final int PARAM_NUM = 0;

    public ID(){
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns current active turtle's ID number. ID values typically start at 1 and increase by 1 with each new turtle created. Note, there is technically only one "active turtle" at any given time since each command is run once for each active turtle, i.e., this value can always be used to identify the current turtle running the command.
     * @return
     * @throws IllegalStateException
     */
    public double execute() throws IllegalStateException {
        return getValue(ID);
    }

}
