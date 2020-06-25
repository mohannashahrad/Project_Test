package graphics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.JacksonSaver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;
import model.Seller;

public class View extends Application {

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
        stage.setOnCloseRequest(event -> {
            JacksonSaver jacksonSaver = new JacksonSaver();
            jacksonSaver.dataSaver();
        });
        mainMenu.run();
        stage.show();
    }
}
