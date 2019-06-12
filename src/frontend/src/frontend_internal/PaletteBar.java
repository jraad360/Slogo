package frontend_internal;

import javafx.scene.layout.Region;

public class PaletteBar extends Bar {

    /**
     * With more time: Contains display of the list of colors (add on images later) associated with an index in a list.
     * Would have been integrated into design by adding the PaletteBar as a tab (in the same manner as the other tabs, which are all bars).
     * Extends Bar class.
     * Would have displayed an HBox containing rectangles of different colors (color associated with list index). The rectangles would have been labeled by list index so the user would know the connection.
     * @author Mary Stuart Elder
     */

    /**
     * As with the other Bar objects, this method is used to generate the HBox region representing the content of the Bar.
     * @return the HBox of the display
     */
    @Override
    protected Region makeDisplay() {
        return null;
    }
}
