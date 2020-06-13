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

public class View extends Application {

    private static final String MAIN_FXML = "fxml/MAinMenu.fxml";
    private static final String LR_FXML = "fxml/LoginRegisterMenu.fxml";
    private static final String PRODUCT_FXML = "fxml/ProductMenu.fxml";

    private static Map<SceneName, FxmlInfo> scenes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*scenes.put(SceneName.MAIN, new FxmlInfo(MAIN_FXML, SceneName.MAIN, stage));
        scenes.put(SceneName.LOGIN_REGISTER, new FxmlInfo(LR_FXML, SceneName.LOGIN_REGISTER, stage));
        scenes.put(SceneName.PRODUCT, new FxmlInfo(PRODUCT_FXML, SceneName.PRODUCT, stage));/*

        stage.setScene(scenes.get(SceneName.MAIN).getScene());
        /**
         * the comments above are real code
         * the below one is for finding the true address
         */
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/ProductMenu.fxml").toURI().toURL());
        Parent mainCallWindowFXML = loader.load();
        stage.setScene(new Scene(mainCallWindowFXML,600,600));
        /**
         * to here
         */
        stage.setTitle("TEAM-18");
        stage.show();
    }

    public static Map<SceneName, FxmlInfo> getScenes() {
        return scenes;
    }

    public static void updateScenes(SceneName name, FxmlInfo info) {
        scenes.put(name, info);
    }

}
