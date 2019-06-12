package backend_internal;

import java.util.Queue;
import java.util.Stack;

/**
 * CommanRunner implements the InternalBackend interface and holds functionality for executing a queue created in the Parser.
 * @author Jamie Palka and Jorge Raad
 */
public class CommandRunner implements InternalBackEnd{
    private Parser myParser;

    public CommandRunner(){
        myParser = new Parser();
        Command.myParser = myParser;
    }

    /**
     * Creates and runs the queue of the user input.
     * @param input
     * @return a double that results from executing the given command(s)
     */
    @Override
    public double parseToExecute(String input) {
        return runQueue(myParser.createQueue(input));
    }

    /**
     * Returns the parser used by CommandRunner. This will be used
     * @return
     */
    public Parser getMyParser(){
        return myParser;
    }

    /**
     * Takes queue of a series of inputted lines/input units and runs them.
     * @param queue - a queue of stacks (each stack represents nested commands - string to string)
     * @return result of the queue - result of the final command executed.
     */
    public static double runQueue(Queue<Stack<InputType>> queue) {
        double result = 0;
        while(queue.size() != 0){
            result = runStack(queue.remove());
        }
        // returns the last number from the sequence of commands
        return result;
    }

    /**
     * Takes queue of a series of inputted lines/input units and runs them.
     * @param inputUnitStack - a stack that represents one command (can be nested)
     * @return double result of the execution (final command to be executed in stack)
     */
    public static double runStack(Stack<InputType> inputUnitStack) {
        Stack<InputType> temp = new Stack<>();
        while (inputUnitStack.size() != 0) {
            InputType current = inputUnitStack.pop();

            if (current instanceof Command) {
                for (int k = 0; k < current.getParamNum(); k++) {
                    if (temp.size() == 0) {
                        throw new IllegalArgumentException("Not enough arguments.");
                    }
                    current.setParameter(temp.pop());
                }
                temp.push(new Constant(current.execute()));
            } else {
                temp.push(current);
            }
        }
        while (temp.size() != 1) {
            // get to bottom of temp stack to get the last constant that should be returned
            temp.pop();
        }
        return temp.pop().execute();
    }

    /**
     * Sets the language given by the frontend.
     * @param language
     */
    @Override
    public void setLanguage(String language) {
        myParser.replacePatterns(language);
    }

}
