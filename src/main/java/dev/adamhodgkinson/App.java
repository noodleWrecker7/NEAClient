package dev.adamhodgkinson;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(960);
        stage.setHeight(540);
        stage.show();
        load();
        Scene root = new Scene(new Pane());
        stage.setScene(root);
        ScreenManager.setRootScene(root);
        ScreenManager.activate("loading.fxml");
        ImageView img = new ImageView();
    }

    private void load() { // for loading of all files: fxml, textures, levels etc
        try {
            // todo this section should be moved to be a part of the game class and initialised only when that's loaded
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File atlasFile = new File(classloader.getResource("Game.xml").getFile());
            TextureSheetManager gameSheet = new TextureSheetManager(atlasFile);

            String name = "loading.fxml";
            ScreenManager.addScreen(name, new FXMLLoader().load(classloader.getResourceAsStream(name)));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading fxml files");
            System.exit(1);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("Error loading textures");
            System.exit(1);
        }
    }
}
