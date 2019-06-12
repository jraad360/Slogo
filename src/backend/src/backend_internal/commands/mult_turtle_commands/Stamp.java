package backend_internal.commands.mult_turtle_commands;

import backend_internal.Command;

public class Stamp extends Command {
    @Override
    public double execute() throws IllegalStateException {
        STAMPS.add(CURRENT_TURTLE_MAP.get(ID));
        return CURRENT_TURTLE_MAP.get(ID);
    }
}
