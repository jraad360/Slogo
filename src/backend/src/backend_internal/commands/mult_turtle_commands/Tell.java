package backend_internal.commands.mult_turtle_commands;

import backend_internal.BracketList;
import backend_internal.Command;

import java.util.Map;

public class Tell extends Command {
    private static final int PARAM_NUM = 1;
    private static final int ID_LIST_INDEX = 0;

    public Tell(){
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: [turtle(s)] ([int(s)]). Sets turtles that will follow commands hereafter. Returns last value in turtles list. Note, if turtle has not previously existed, it is created and placed at the home location. Note, if more than one turtle is active, commands run return value associated with the last active turtle.
     * @return
     * @throws IllegalStateException
     */
    public double execute() throws IllegalStateException {
        String[] IDs = ((BracketList)myParameters.get(ID_LIST_INDEX)).getString().trim().split(" ");
        double ans = 0;
//        for(String s : IDs){
//            CURRENT_TURTLE_MAP = getTurtle(Integer.parseInt(s));
//            CURRENT_TURTLE_MAP.put(ACTIVE, 1.0);
//        }
        for(Map<String, Double> turtle : LIST_OF_TURTLES) {
            CURRENT_TURTLE_MAP = turtle;
            if (containsID(IDs, (int)getValue(ID))){
                ans = getValue(ID);
                setValue(ACTIVE, 1.0);
            }
            else{
                setValue(ACTIVE, 0.0);
            }
        }
        return ans;
    }

    private boolean containsID(String[] array, int id){
        if (array[0].equals("")) {
            return false;
        }
        for(String s : array){
            if (Integer.parseInt(s) == id){
                return true;
            }
        }
        return false;
    }

}
