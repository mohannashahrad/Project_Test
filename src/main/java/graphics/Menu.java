package graphics;
import controller.Manager;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Person;


public class Menu {
    protected static Stage stage;
    protected static Person person;
    protected static Manager manager = new Manager();
    public void setStage(Stage stage) {
        this.stage = stage;
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

}
