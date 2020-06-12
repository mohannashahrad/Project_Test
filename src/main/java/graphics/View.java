package graphics;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {

    private static final String MAIN_FXML = "/fxml/main-view.fxml";
    private static final String SCENE_ONE_FXML = "/fxml/scene-one.fxml";
    private static final String SCENE_TWO_FXML = "/fxml/scene-two.fxml";
    private static final String SCENE_THREE_FXML = "/fxml/scene-three.fxml";

    private static Map<SceneName, FxmlInfo> scenes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        scenes.put(SceneName.MAIN, new FxmlInfo(MAIN_FXML, SceneName.MAIN, stage));
        scenes.put(SceneName.SCENE1, new FxmlInfo(SCENE_ONE_FXML, SceneName.SCENE1, stage));
        scenes.put(SceneName.SCENE2, new FxmlInfo(SCENE_TWO_FXML, SceneName.SCENE2, stage));
        scenes.put(SceneName.SCENE3, new FxmlInfo(SCENE_THREE_FXML, SceneName.SCENE3, stage));

        stage.setScene(scenes.get(SceneName.MAIN).getScene());
        stage.setTitle("Multi-Scene Demo");
        stage.show();
    }

    public static Map<SceneName, FxmlInfo> getScenes() {
        return scenes;
    }

    public static void updateScenes(SceneName name, FxmlInfo info) {
        scenes.put(name, info);
    }

}
