package backend_internal;

/**
 * @author Mary Stuart Elder
 *
 * The internal portion of the backend is largely built up of functions (members of the Function superclass). This portion of the API mostly offers functionality for checking the validity of user input strings and modifying parameters based on user input. The Function superclass and its subclasses will build up this class, along with an error checking class (ErrorChecker). ErrorChecker performs a series of tests on the user input string to validate its validity in checkError. If the string is valid, checkError returns 0. Otherwise, a number will be returned and an IllegalArgumentException will be thrown. The ErrorChecker class will offer methods to check for valid syntax, command inputs, and more (all executed in the checkError method). The Function hierarchy performs simple modifications to environment variables and objects.
 */
interface InternalBackEnd{

    /**
     * Executes the given user input.
     */
     double parseToExecute(String input) throws IllegalStateException;

    /**
     * Called from external backend in order to replace the Parser's current language to the specified language.
     * @param language - desired language to switch into
     */
    void setLanguage(String language);
}