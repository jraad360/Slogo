package backend_internal;

import java.util.Queue;
import java.util.Stack;

import static backend_internal.Command.myParser;

/**
 * A BracketList implements the interface InputType and is used in complex commands.
 * @author Jamie Palka and Jorge Raad
 */
public class BracketList implements InputType {

    private String bracketContents;
    private static final int PARAM_NUM = 0;

    public int getParamNum() {
        return PARAM_NUM;
    }

    @Override
    public void setParameter(InputType parameter) { }

    @Override
    public void setParameter(String parameter) {
        bracketContents = parameter;
    }

    /**
     * Returns the String stored in the BracketList that contains the commands within the BracketList.
     * @return - string containing commands to be executed.
     */
    public String getString() {
        return bracketContents;
    }

    @Override
    /**
     * {@inheritDoc}
     * For a BracketList, the execute command treats its contents as an independent command which is run. The result of
     * running these contents is returned.
     */
    public double execute() {
//        System.out.println("Executing BracketList - " + bracketContents);
        Queue myQueue = myParser.createQueue(bracketContents);
        return CommandRunner.runQueue(myQueue);
    }
}
