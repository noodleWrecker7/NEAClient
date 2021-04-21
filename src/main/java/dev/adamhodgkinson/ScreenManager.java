package dev.adamhodgkinson;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenManager { /* Manages the screens (fxml files) so they can be interchanged programmatically
                                within a single stage */
    static private HashMap<String, Pane> screenMap = new HashMap<>(); // screens stored by name
    static private Scene main; // root scene of the stage where the data is injected
    static private String currentPageTitle = null; // title of current page

    public static void setRootScene(Scene _main) {
        // _main is a reference to the root of the stage which means it can be
        // changed by this class and affect the main stage
        main = _main;
    }

    protected static void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    protected static void removeScreen(String name) {
        screenMap.remove(name);
    }

    public static String getCurrentPageTitle() {
        return currentPageTitle;
    }

    public static Pane getPane(String name) {
        return screenMap.get(name);
    }

    public static void activate(String name) {
        // injects the pane found under the specified name into the root of the stage

        Pane pane = screenMap.get(name);
        main.setRoot(pane);
        currentPageTitle = name;
    }
}