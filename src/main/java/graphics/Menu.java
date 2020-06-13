package graphics;
import javafx.stage.Stage;
import model.Person;


public class Menu {
    protected static Stage stage;
    protected static Person person;
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Menu.person = person;
    }



}
