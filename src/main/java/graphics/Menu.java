package graphics;
import controller.Manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Person;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class Menu {
    protected static Stage stage = View.getStage();
    protected static Person person;
    protected static Manager manager = new Manager();
    private Parent root;
    private Menu previousMenu;
    private String fxmlPath;

    public Menu(Menu previousMenu,String fxmlPath) {
        this.previousMenu = previousMenu;
        this.fxmlPath = fxmlPath;
    }

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Menu.person = person;
    }

    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();
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
