package frontend_external;

import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class simply displays an alert with a specific error message every time an exception is thrown.
 * It assumes that the reason for the error will be known by the class that throws the error and takes this in as a string.
 * If the error handler class does not have information regarding the type of error thrown, a general error message is displayed.
 * A new instance of this class is created every time an error is thrown.
 *
 * @author Eric Werbel
 */
public class ErrorHandler {

    /**
     * Instantiates an ErrorHandler object which serves to display the error to the screen.
     * When a function catches an exception this method is called with a given string to determine the error type and
     * displays the correct error message.
     *
     * @param errorType = String determining what type of error message to display
     */
    public ErrorHandler(String errorType) {
        showError(errorType);
    }

    /**
     * Creates an Alert to let the user know that there was an error.
     * Provides feedback as to the type of error so it can be corrected by the user.
     *
     * @param errorType = String determining what type of error message to display
     */
    private void showError(String errorType) {
        Alert myError = new Alert(Alert.AlertType.ERROR);
        myError.setTitle("Error Detected");
        try {
            determineErrorText(myError, errorType);
        } catch (IOException e){
            myError.setHeaderText("Unknown Error");
            myError.setContentText("The source of your error is unknown.");
        }
        myError.showAndWait();
    }

    /**
     * Reads from a properties file using the error type string as a key.
     * The resulting value is then displayed on the Alert to let the user know what caused the error.
     *
     * @param myError = Alert created in showError() where the error message will be displayed
     * @param errorType = String corresponding to the key in the properties file stating what type of error to display.
     * @throws IOException in case properties file cannot be found
     */
    private void determineErrorText(Alert myError, String errorType) throws IOException {
        Properties errorProperties = new Properties();
        FileInputStream ip = new FileInputStream("data/ErrorType.properties");
        errorProperties.load(ip);
        String val = errorProperties.getProperty(errorType);
        String [] myMessage = val.split("-");
        myError.setHeaderText(myMessage[0]);
        myError.setContentText(myMessage[1]);
    }

}
