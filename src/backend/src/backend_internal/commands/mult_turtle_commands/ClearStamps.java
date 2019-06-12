package backend_internal.commands.mult_turtle_commands;

import backend_internal.Command;

import java.util.ArrayList;

public class ClearStamps extends Command {
    @Override
    public double execute() throws IllegalStateException {
        if (STAMPS.size() > 0 ) {
            STAMPS.removeAll(STAMPS);
            return 1;
        }
        return 0;
    }
}
