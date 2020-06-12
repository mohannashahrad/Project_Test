package graphics;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
    private static Stage guiStage;
    public static Stage getStage() {
        return guiStage;
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        guiStage = primaryStage;
        guiStage.setTitle("Team-18");
        guiStage.setScene();
        guiStage.show();
    }
}
