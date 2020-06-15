package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegisterMenu extends Menu implements Initializable {
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField name;
    @FXML
    public TextField familyName;
    @FXML
    public TextField email;
    @FXML
    public TextField number;
    @FXML
    public Label message;
    @FXML
    public Label companyLabel;
    @FXML
    public TextField company;
    @FXML
    public CheckBox adminRole;
    @FXML
    public CheckBox sellerRole;
    @FXML
    public CheckBox customerRole;

    public RegisterMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/RegisterMenu.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyLabel.visibleProperty().bind(sellerRole.selectedProperty());
        company.visibleProperty().bind(sellerRole.selectedProperty());
    }

    public void register(ActionEvent actionEvent) {
        message.setText("");
        String username = this.username.getText();
        String password = this.password.getText();
        String name = this.name.getText();
        String familyName = this.familyName.getText();
        String email = this.email.getText();
        String number = this.number.getText();
        if (!adminRole.isSelected() && !sellerRole.isSelected() && !customerRole.isSelected()) {
            message.setText("please choose your role!");
            return;
        }
        if (adminRole.isSelected() && manager.doesAnyAdminExist()) {
            message.setText("Only admins can add admins!");
            return;
        }
        if (manager.doesUsernameExist(username)) {
            message.setText("username already exists!");
            return;
        }
        HashMap<String, String> data = new HashMap<>();
        if (sellerRole.isSelected()) {
            String company = this.company.getText();
            data.put("company", company);
        }

        data.put("username", username);
        data.put("password", password);
        data.put("role", roleGetter());
        data.put("name", name);
        data.put("familyName", familyName);
        data.put("email", email);
        data.put("number", number);
        try {
            manager.register(data);
            message.setText("register successful!");
        } catch (Exception e) {
            message.setText(e.getMessage());
        }
    }

    private String roleGetter() {
        if (sellerRole.isSelected()) {
            return "seller";
        } else if (customerRole.isSelected()) {
            return "customer";
        }
        return "admin";
    }

}


