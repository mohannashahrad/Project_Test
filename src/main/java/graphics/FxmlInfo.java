package graphics;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.snortum.javafx.multiscenefxml.model.SceneName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Holds FXML information:<br>
 * <ul>
 *   <li>the resource name for the FXML file</li>
 *   <li>the {@link net.snortum.javafx.multiscenefxml.model.SceneName}</li>
 *   <li>the primary stage, if needed by the controller</li>
 *   <li>the scene for this FXML, iff it has been loaded and set</li>
 * </ul>
 * 
 * The scenes are loaded lazily, that is, only the first time they are called.
 * After that, the loaded scene is looked and returned.
 * 
 * @author Knute Snortum
 * @version 2019-01-30
 */
public class FxmlInfo {
	
	private static Logger logger = LogManager.getLogger();
	
	private String resourceName;
	private net.snortum.javafx.multiscenefxml.model.SceneName sceneName;
	private Stage stage;
	private Scene scene;
	
	/**
	 * Construct an FxmlInfo object
	 * 
	 * @param resourceName the resource name for this FXML
	 * @param sceneName the {@link net.snortum.javafx.multiscenefxml.model.SceneName} for this FXML
	 * @param stage the primary stage that the scene will be set to
	 */
	public FxmlInfo(String resourceName, net.snortum.javafx.multiscenefxml.model.SceneName sceneName, Stage stage) {
		this.resourceName = resourceName;
		this.sceneName = sceneName;
		this.stage = stage;
	}
	
	/** @return the resource name for this FXML file */
	public String getResourceName() {
		return resourceName;
	}
	
	/** @return the {@link net.snortum.javafx.multiscenefxml.model.SceneName} for this FXML file */
	public net.snortum.javafx.multiscenefxml.model.SceneName getSceneName() {
		return sceneName;
	}
	
	/** @param scene the scene to set, loaded from this FxmlInfo */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * Builds the scene iff {@code scene} is {@code null}.  Then it returns the scene to the caller.
	 * 
	 *  @return the scene 
	 */
	public Scene getScene() {
		if (scene == null) {
			scene = new net.snortum.javafx.multiscenefxml.util.FxmlLoad().load(this);
			if (logger.isInfoEnabled()) {
				logger.info("{} has been built", sceneName);
			}
		}
		return scene;
	}
	
	/** @return {@code true} if the scene has been built, otherwise {@code false} */
	public boolean hasScene() {
		return scene != null;
	}
	
	/** @return the primary stage for this Scene */
	public Stage getStage() {
		return stage;
	}
	
}
