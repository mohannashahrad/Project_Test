package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Admin;
import model.Person;
import model.Seller;

public class LoginMenu extends Menu {
    public LoginMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/LoginMenu.fxml");
    }
    @FXML public TextField username;
    @FXML public Label usernameMessage;
    @FXML public TextField password;
    @FXML public Label passwordMessage;
    public void goToRegisterMenu(ActionEvent actionEvent) {
        RegisterMenu registerMenu = new RegisterMenu(this);
        registerMenu.run();
    }

    public void login(ActionEvent actionEvent) {
        usernameMessage.setText("");
        passwordMessage.setText("");
        String username = this.username.getText();
        String password = this.password.getText();
        if (!manager.doesUsernameExist(username)) {
            usernameMessage.setText("username does not exist");
        }
        try {
            Menu.setPerson(manager.login(username, password));
            goToAccount(person);

        } catch (Exception e) {
            passwordMessage.setText(e.getMessage());
        }
    }

    private void goToAccount(Person person){
        if (person == null) {
            System.out.println("You need to login to access account menu.");
            LoginMenu loginRegisterMenu = new LoginMenu(this.getPreviousMenu());
            loginRegisterMenu.run();
        } else if (person instanceof Admin) {
            AdminMenu adminMenu = new AdminMenu(this.getPreviousMenu());
            adminMenu.run();
        } else if (person instanceof Seller) {
            SellerMenu sellerMenu = new SellerMenu(this.getPreviousMenu());
            sellerMenu.run();
        } else {
            CustomerMenu customerMenu = new CustomerMenu(this.getPreviousMenu());
            customerMenu.run();
        }
    }
}
