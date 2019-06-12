package backend_internal.commands.mult_turtle_commands;

import backend_internal.BracketList;
import backend_internal.Command;

public class Ask extends Command {
    private static final int PARAM_NUM = 2;
    private static final int ID_LIST_INDEX = 0;
    private static final int COMMANDS_INDEX = 1;

    public Ask(){
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: [turtle(s)][command(s)] ([int(s)][String(s)]). Only the turtles given in first list all run commands given in the second list. Returns result of last command run by the last turtle. Note, after commands are run, currently active list of turtles returns to that set by the last TELL command (or default active turtle if TELL never given). Note, if more than one turtle is active, commands run return value associated with the last active turtle.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        String[] IDs = ((BracketList)myParameters.get(ID_LIST_INDEX)).getString().trim().split(" ");
        double ans = 0;
        for(String s : IDs){
            CURRENT_TURTLE_MAP = getTurtle(Integer.parseInt(s));
            if(CURRENT_TURTLE_MAP != null){
                myParameters.get(COMMANDS_INDEX).execute();
            }
        }
        return ans;
    }
}
