package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class LoginMenu extends Menu {
    public LoginMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/LoginMenu.fxml");
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

    public void goToRegisterMenu(ActionEvent actionEvent) {
    }

    public void login(ActionEvent actionEvent) {
    }
}
