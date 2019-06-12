package backend_internal;

/**
 * Interface that represents any type of input a user can type.
 * @author Jamie Palka and Jorge Raad
 */
public interface InputType {

    /**
     * Returns the number of arguments the InputType needs to have before being executed. This will always be 0 for
     * anything other than a Command. Some Commands may also return 0 if they do not require arguments.
     * @return - (int) number of arguments required
     */
    int getParamNum();

    /**
     * Used to add a parameter to an InputType. Since Commands are the only InputTypes to take parameters, this would
     * only be used by Commands.
     * @param parameter
     */
    void setParameter(InputType parameter);

    /**
     * Used within the InputTypeFactory. This method takes the String input corresponding to the InputType and stores
     * relevant information.
     * @param parameter - (String) user input
     */
    void setParameter(String parameter);

    /**
     * Always returns the number represented by an InputType. May affect turtles' information depending if it is a Command.
     * @return - (double) number resulting from executing
     * @throws IllegalStateException
     */
    double execute() throws IllegalStateException;

}
