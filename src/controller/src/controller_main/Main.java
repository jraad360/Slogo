package controller_main;

import backend_external.BackendController;
import frontend_external.FrontendController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * 
     * Used by both the front and backend to control communication
     * @author Mary Stuart Elder, Eric Werbel, Jamie Palka, Jorge Raad
     */
    
    // TODO: add dropdown for turtle file selection

    /**
     * Launches simulation
     * @param args Standard arguments
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Method required for Application subclasses.
     * Initializes the backend and frontend controllers.
     * @param stage Default stage input
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Slogo");
        BackendController backend = new BackendController();
        FrontendController frontend = new FrontendController(stage, backend);
    }
}

