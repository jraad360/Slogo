package frontend_internal;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public abstract class Bar extends Display{

    /**
     * This class extends the Display class.
     * Subclasses of Bar contain a "bar", here an HBox, used to store object data for display.
     * The createSpacer method is used for building the bar in a well spaced way.
     * Assumes subclasses will build the Bar object, possibly through the makeDisplay method of the superclass.
     * @author Mary Stuart Elder
     */

    protected HBox myBar;

    /**
     * This method is used by subclasses to pad out the HBox in a dynamic manner.
     * The setHgrow method always spacer to grow to fill the remaining space in the HBox.
     * @return Node spacer, a region
     */
    protected Node createSpacer() {
        final Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    /**
     * Used by external classes to get the HBox built by a Bar object.
     * Assumes the Bar's makeDisplay method has been called, otherwise will return null for the myBar value.
     * @return HBox myBar, the bar for display
     */
    public HBox getBar(){
        return myBar;
    }
}
