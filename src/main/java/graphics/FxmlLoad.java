package graphics;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class FxmlLoad {

	public Scene load(FxmlInfo fxmlInfo) {

		if (fxmlInfo.hasScene()) {
			return fxmlInfo.getScene();
		}

		URL url = getClass().getResource(fxmlInfo.getResourceName());

		FXMLLoader loader = new FXMLLoader(url);
		Scene scene;

		try {
			scene = new Scene(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			Platform.exit();
			return null;
		}

		// Write back the updated FxmlInfo to the scenes Map in Main
		fxmlInfo.setScene(scene);
		View.updateScenes(fxmlInfo.getSceneName(), fxmlInfo);

		Menu controller = loader.getController();
		if (controller != null) {
			controller.setStage(fxmlInfo.getStage());
		}

		return scene;
	}
}
