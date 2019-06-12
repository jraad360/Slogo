package backend_internal.commands.math_operations;

import backend_internal.Command;

public class NaturalLog extends Command {
    private static final int PARAM_NUM = 1;

    public NaturalLog(){
        super();
        myParamNumber = PARAM_NUM;
    }

    /**
     * Parameters: expr (int). Returns natural log of expr.
     * @return
     */
    @Override
    public double execute() {
        checkError();
        return Command.round(Math.log(myParameters.get(0).execute()));
    }

    @Override
    protected void checkError() throws IllegalArgumentException {
        super.checkError();
        if(myParameters.get(0).execute() <= 0){
            throw new IllegalArgumentException("Cannot take log of number <= 0.");
        }
    }
}
