package backend_internal.commands.complex_commands;

import backend_internal.Command;
import backend_internal.Variable;

/**
 * Implements the logic for the IfElse command. Extends command.
 * @author Jamie Palka
 */
public class MakeVariable extends Command {

    private static final int PARAM_NUM = 2;
    private static int VARIABLE_INDEX = 0;
    private static int VALUE_INDEX = 1;

    public MakeVariable(){
        super();
        myParamNumber = PARAM_NUM;
    }

    @Override
    public int getParamNum() {
        return PARAM_NUM;
    }

    /**
     * Parameters: variable expr (String int). Assigns the value of expr to variable, creating the variable if necessary.
     * @return - Returns expr.
     */
    @Override
    public double execute() {
        checkError();
        Variable variable = (Variable)myParameters.get(VARIABLE_INDEX);
        variable.setNumber(myParameters.get(VALUE_INDEX).execute());
        return myParameters.get(VALUE_INDEX).execute();
    }
}