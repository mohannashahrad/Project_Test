package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Admin;
import model.Seller;

import java.io.File;
import java.io.IOException;

public class MainMenu extends Menu {
    public MainMenu(Menu previousMenu) {
        super(previousMenu,"src/main/java/graphics/fxml/MainMenu.fxml");
    }

    public void goToSalesMenu(ActionEvent actionEvent) {
    }

    public void goToProductsMenu(ActionEvent actionEvent) {
    }

    public void goToAccount(ActionEvent actionEvent) {

    }
}
