package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Admin;
import model.Seller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainMenu extends Menu {
    public MainMenu(Menu previousMenu) {
        super(previousMenu,"src/main/java/graphics/fxml/MainMenu.fxml");
    }

    public void goToSalesMenu(ActionEvent actionEvent) {
    }

    public void goToProductsMenu(ActionEvent actionEvent) {
    }

    public void goToAccount(ActionEvent actionEvent) {
        if (person == null) {
            LoginMenu loginMenu = new LoginMenu(this);
            loginMenu.run();
        } else if (person instanceof Admin) {
            AdminMenu adminMenu = new AdminMenu(this);
            adminMenu.run();
        } else if (person instanceof Seller) {
            SellerMenu sellerMenu = new SellerMenu(this);
            sellerMenu.run();
        } else {
            CustomerMenu customerMenu = new CustomerMenu(this);
            customerMenu.run();
        }
    }
    public void run(){
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File(fxmlPath).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            loader.setControllerFactory(c -> this);
            root = loader.load();
            stage.setScene(new Scene(root,600,600));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
