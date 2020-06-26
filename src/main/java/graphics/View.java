package graphics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.FileSaver;
import controller.Storage;
import javafx.application.Application;
import javafx.stage.Stage;


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
        stage.setTitle("TEAM-18");
        stage.setOnCloseRequest(event -> {
            FileSaver fileSaver = new FileSaver(Storage.getStorage());
            fileSaver.dataSaver();
        });
        mainMenu.run();
        stage.show();
    }
}
