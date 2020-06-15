package graphics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class AdminMenu extends Menu {
    public AdminMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/AdminMenu.fxml");
    }
    public void run() {
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(fxmlPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            loader.setControllerFactory(c -> this);
            root = loader.load();
            stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
