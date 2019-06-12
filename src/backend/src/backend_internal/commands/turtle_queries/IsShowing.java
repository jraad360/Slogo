package backend_internal.commands.turtle_queries;

import backend_internal.Command;

public class IsShowing extends Command {
    private static final int PARAM_NUM = 0;

    public IsShowing(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: none. Returns 1 if turtle is showing, 0 if it is hiding.
     * @return
     * @throws IllegalStateException
     */
    @Override
    public double execute() throws IllegalStateException {
        checkError();
        double showing = getValue(SHOWING);
        if(showing != 0){
            return 1;
        }
        return 0;
    }
}
