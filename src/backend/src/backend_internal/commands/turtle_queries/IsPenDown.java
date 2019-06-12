package backend_internal.commands.turtle_queries;

import backend_internal.Command;

public class IsPenDown extends Command {
    private static final int PARAM_NUM = 0;

    public IsPenDown(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns 1 if turtle's pen is down, 0 if it is up.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        double penstate = getValue(PEN_DOWN);
        if(penstate != 0){
            return 1;
        }
        return 0;
    }
}
