package frontend_internal;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public abstract class ScrollPaneBar extends Bar {

    /**
     * This class extends Bar to create a special bar with scroll pane content.
     * The specific subclass was created because many Bars benefited from ScrollPane functionality, so having a method to call for creating a formatted ScrollPane reduces duplication and further classifies the Bar object.
     * @author Mary Stuart Elder
     */

    /**
     * This method is used by subclasses of ScrollPaneBar to generate an initial ScrollPane object with simply a title and preferred dimensions.
     * Assumes valid, nonnegative int inputs (as only internal methods using static constants call this method)
     * @param titleText String, the text of the title of the ScrollPane
     * @param width int, the preferred width of the ScrollPane
     * @param height int, the preferred height of the ScrollPane
     * @return ScrollPane for use in the Bar
     */
    protected ScrollPane makeScrollPane(String titleText, int width, int height){
        var pane = new ScrollPane();
        pane.setPrefViewportWidth(width);
        pane.setPrefViewportHeight(height);
        var title = new Text(titleText);
        pane.setContent(title);
        return pane;
    }
}
