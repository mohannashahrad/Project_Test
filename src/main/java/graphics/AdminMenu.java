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

}
