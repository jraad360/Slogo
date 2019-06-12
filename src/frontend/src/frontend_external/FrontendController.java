package frontend_external;

import backend_external.ExternalBackend;
import frontend_internal.CommandLine;
import frontend_internal.SlogoView;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Intended to be passed into the backend in order to allow the backend to directly update the frontend Turtle objects.
 * Currently used to initiate the frontend objects and take a backend interface.
 * The backend interface is passed into the frontend and allows the frontend to call backend methods when necessary and is the main way frontend and backend communicate with one another.
 * Dependent on the backend module for the interface and assumes that all other frontend classes that need the interface will be able to get it from one another.
 *
 * @author Mary Stuart Elder and Eric Werbel
 */
public class FrontendController {

    private Map<Integer, Turtle> myTurtles;
    private CommandLine myCommandLine;

    /**
     * Initializes all process in the frontend and has an instance of the backend interface that it can give to different classes.
     * Allows the necessary backend methods to be passed into the frontend and be called without giving any objects.
     *
     * @param stage = original stage where all objects to be displayed go
     * @param backend = instance of the external backend interface that allows frontend to call backend methods
     */
    public FrontendController(Stage stage, ExternalBackend backend) {
        SlogoView view = new SlogoView(backend);
        stage.setScene(view.getScene());
        stage.show();
        myTurtles = view.getTurtles();
        /*for (Turtle t : myTurtles) {
            view.initializeTurtle(t);
        }*/
        myCommandLine = view.getCommandLine();
    }

//
//    @Override
//    public void setX(double x) {
//        myTurtle.setX(x);
//    }
//
//    @Override
//    public void setY(double y) {
//        myTurtle.setY(y);
//    }
//
//    @Override
//    public double getX() {
//         return myTurtle.getX();
//    }
//
//    @Override
//    public double getY() {
//        return myTurtle.getY();
//    }
//
//    @Override
//    public void updateOrientation(double orientation) {
//        myTurtle.updateOrientation(orientation);
//    }
//
//    @Override
//    public double getOrientation() {
//        return myTurtle.getOrientation();
//    }
//
//    @Override
//    public String getUserInput() {
//        return myCommandLine.getTextField().getText();
//    }
//
//    @Override
//    public void showError(String errorType) {
//        new ErrorHandler(errorType);
//    }
}
