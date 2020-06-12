package graphics;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {

    private static final String MAIN_FXML = "/fxml/MAinMenu.fxml";
    private static final String LR_FXML = "/fxml/LoginRegisterMenu.fxml";


    private static Map<SceneName, FxmlInfo> scenes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        scenes.put(SceneName.MAIN, new FxmlInfo(MAIN_FXML, SceneName.MAIN, stage));
        scenes.put(SceneName.LOGIN_REGISTER, new FxmlInfo(LR_FXML, SceneName.LOGIN_REGISTER, stage));

        stage.setScene(scenes.get(SceneName.MAIN).getScene());
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
