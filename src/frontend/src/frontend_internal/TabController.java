package frontend_internal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ContextMenuEvent;

public class TabController {

    /**
     * Assumes only one of each view can exist -> only one tab can be filled with the contents at a time
     * Sources consulted:
     *      https://stackoverflow.com/questions/16437615/javafx-tab-positioning-on-mouse-drag-drop?rq=1
     *      https://o7planning.org/en/11115/javafx-contextmenu-tutorial
     *      https://stackoverflow.com/questions/34008793/javafx-tabpane-context-menu-depending-on-selected-tab
     * Class used to organize Bars into Tabs with a ContextMenu used for navigation.
     * This class is added to the top of the BorderPane in SlogoView and can contain as many or as few of the tabs options as a user desires.
     * @author Mary Stuart Elder
     */

    private TabPane myTabPane = new TabPane();
    private ContextMenu myContextMenu = new ContextMenu();
    private EnvironmentInfoBar myEnvironmentBar;
    private NavBar myNavBar;
    private TurtleInfoBar myTurtleInfoBar;

    // TODO: Replace text with properties file reference?
    public static final String ENVIRONMENT_INFO_TEXT = "Environment Info";
    public static final String NAVIGATION_TEXT = "Navigation";
    public static final String TURTLE_INFO_TEXT = "Turtle Info";
    public static final String ADD_TAB_START = "Add ";
    public static final String ADD_TAB_END = " Tab";
    public static final String MOVE_RIGHT_TEXT = "Move tab right";
    public static final String MOVE_LEFT_TEXT = "Move tab left";

    public static final int VALID_TAB_CUTOFF = -1;
    public static final int VALID_SIZE_CUTOFF = 1;
    public static final int RIGHT_ADJUSTMENT = 1;
    public static final int LEFT_ADJUSTMENT = -1;
    public static final int LEFT_END_INDEX = 0;

    /**
     * Constructor, takes in arguments from SlogoView.
     * Initializes tabs to display all possible tab options.
     * @param envBar EnvironmentInfoBar passed in from SlogoView
     * @param navBar NavBar passed in from SlogoView
     * @param infoBar TurtleInfoBar passed in from SlogoView
     */
    public TabController(EnvironmentInfoBar envBar, NavBar navBar, TurtleInfoBar infoBar){
        myEnvironmentBar = envBar;
        myNavBar = navBar;
        myTurtleInfoBar = infoBar;
        initializeTabs();
    }

    // TODO: modify moving to be drag and drop (not ContextMenu driven)


    /**
     * Called from constructor, generates all possible tabs and the associated ContextMenu actions for adding them.
     */
    private void initializeTabs(){
        var menuItems = myContextMenu.getItems();
        var environmentText = ADD_TAB_START + ENVIRONMENT_INFO_TEXT + ADD_TAB_END;
        var navigationText = ADD_TAB_START + NAVIGATION_TEXT + ADD_TAB_END;
        var turtleText = ADD_TAB_START + TURTLE_INFO_TEXT + ADD_TAB_END;

        menuItems.add(addMenuItem(environmentText, e -> openTab(ENVIRONMENT_INFO_TEXT, myEnvironmentBar)));
        menuItems.add(addMenuItem(navigationText, e -> openTab(NAVIGATION_TEXT, myNavBar)));
        menuItems.add(addMenuItem(turtleText, e -> openTab(TURTLE_INFO_TEXT, myTurtleInfoBar)));
        menuItems.add(addMenuItem(MOVE_RIGHT_TEXT, e -> moveTab(myTabPane.getTabs().size() - 1, RIGHT_ADJUSTMENT)));
        menuItems.add(addMenuItem(MOVE_LEFT_TEXT, e -> moveTab(LEFT_END_INDEX, LEFT_ADJUSTMENT)));

        myTabPane.setOnContextMenuRequested(event -> showContextMenu(event));
        openTab(ENVIRONMENT_INFO_TEXT, myEnvironmentBar);
        openTab(NAVIGATION_TEXT, myNavBar);
        openTab(TURTLE_INFO_TEXT, myTurtleInfoBar);
    }

    /**
     * Called on right click in TabPane, displays the ContextMenu to the user
     * @param event ContextMenuEvent, called on right click
     */
    private void showContextMenu(ContextMenuEvent event){
        myContextMenu.show(myTabPane, event.getScreenX(), event.getScreenY());
    }

    /**
     * Assumes a Tab can only be built from myBar from a Bar object.
     * The object returned from the getBar() method may change, however, to any Region and this method will still work.
     * @param tabName String name of tab
     * @param tabContents Bar generic Bar object to be passed in
     */
    private void openTab(String tabName, Bar tabContents){
        var tab = new Tab(tabName);
        tab.setContent(tabContents.getBar());
        myTabPane.getTabs().add(tab);
    }

    /**
     * This method allows for either left or right tab movement based on parameters.
     * endIndex assumed to be a valid index (no more than length-1 and no less than 0).
     * adjustment can be any value given a valid endIndex.
     * @param endIndex int, the last possible index a tab can be moved to in a direction
     * @param adjustment int, the increment by which the tab's position will be moved (positive for right, negative for left)
     */
    private void moveTab(int endIndex, int adjustment){
        var currentIndex = myTabPane.getSelectionModel().getSelectedIndex();
        var currentTabCount = myTabPane.getTabs().size();
        // Is a valid pane selected?
        if(currentIndex <= VALID_TAB_CUTOFF || currentTabCount <= VALID_SIZE_CUTOFF || currentIndex == endIndex){
            return;
        }
        var tabToMove = myTabPane.getTabs().get(currentIndex);
        myTabPane.getTabs().remove(tabToMove);
        myTabPane.getTabs().add(currentIndex + adjustment, tabToMove);
        myTabPane.getSelectionModel().select(currentIndex + adjustment);
    }

    /**
     * Adds possible user options to ContextMenu.
     * @param itemText String text of MenuItem
     * @param handler Method to be called on clicking the option
     * @return MenuItem to add to ContextMenu
     */
    private MenuItem addMenuItem(String itemText, EventHandler<ActionEvent> handler){
        var menuItem = new MenuItem(itemText);
        menuItem.setOnAction(handler);
        return menuItem;
    }

    /**
     * Called from SlogoView to get the TabPane.
     * Used to display the TabPane to the user in the overall Scene.
     * @return TabPane myTabPane
     */
    public TabPane getTabController(){
        return myTabPane;
    }
}
