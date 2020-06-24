package graphics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;
import model.Seller;

public class View extends Application {

    private static final String MAIN_FXML = "fxml/MAinMenu.fxml";
    private static final String LR_FXML = "fxml/LoginMenu.fxml";
    private static final String PRODUCT_FXML = "fxml/ProductMenu.fxml";

    public static Stage mainStage;

    public static Stage getStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        MainMenu mainMenu = new MainMenu(null);
        //SellerMenu mainMenu = new SellerMenu(null);
        stage.setTitle("TEAM-18");
        mainMenu.run();
        stage.show();
    }


}
