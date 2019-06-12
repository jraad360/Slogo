package backend_internal.commands.mult_turtle_commands;

import backend_internal.Command;
import backend_internal.commands.complex_commands.If;

import java.util.Map;

public class AskWith extends Command {
    private static final int PARAM_NUM = 2;
    private static final int CONDITION_INDEX = 0;
    private static final int COMMANDS_INDEX = 1;

    public AskWith(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: [condition][command(s)] ([][int(s)]). Tell turtles matching given condition to run commands given in the second list. Returns result of last command run. Note, after commands are run, currently active list of turtles returns to that set by the last TELL command (or default active turtle if TELL never given). Note, if more than one turtle is active, commands run return value associated with the last active turtle.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {

        double ans = 0;
        for(Map<String, Double> turtle : LIST_OF_TURTLES){
            double myID = turtle.get(ID);
            CURRENT_TURTLE_MAP = getTurtle((int) myID);
            if(CURRENT_TURTLE_MAP != null){
                Command command = new If();
                command.setParameter(myParameters.get(CONDITION_INDEX));
                command.setParameter(myParameters.get(COMMANDS_INDEX));
                command.execute();
            }
        }
        return ans;
    }
}
