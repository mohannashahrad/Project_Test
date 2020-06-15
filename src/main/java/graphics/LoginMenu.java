package graphics;

import javafx.event.ActionEvent;

public class LoginMenu extends Menu {
    public LoginMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/LoginMenu.fxml");
    }

    public void goToRegisterMenu(ActionEvent actionEvent) {
        RegisterMenu registerMenu = new RegisterMenu(this);
        registerMenu.run();
    }

    public void login(ActionEvent actionEvent) {

    }
}
