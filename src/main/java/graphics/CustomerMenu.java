package graphics;

import controller.CustomerManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenu extends Menu implements Initializable {
    private CustomerManager customerManager = new CustomerManager();
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label familyNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label balanceLabel;

    public CustomerMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/CustomerMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewPersonalInfo();
    }

    public void viewPersonalInfo() {
        usernameLabel.setText(person.getUsername());
        passwordLabel.setText(person.getPassword());
        nameLabel.setText(person.getName());
        familyNameLabel.setText(person.getFamilyName());
        emailLabel.setText(person.getEmail());
        numberLabel.setText(person.getNumber());
        balanceLabel.setText(Double.toString(person.getBalance()));
        roleLabel.setText("customer");
    }

    public void editPasswordField() {
        String newPassword = editFieldsView("password");
        try {
            manager.editField("password", newPassword);
            viewPersonalInfo();
        } catch (Exception e) {
            showError("Invalid password format!(Use figures or letters)");
        }
    }

    public void editNameField() {
        String newName = editFieldsView("name");
        try {
            manager.editField("name", newName);
            viewPersonalInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editFamilyNameField() {
        String newFamilyName = editFieldsView("family name");
        try {
            manager.editField("familyName", newFamilyName);
            viewPersonalInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editBalanceField() {
        String newBalance = editFieldsView("balance");
        try {
            customerManager.addBalance(Double.parseDouble(newBalance));
            viewPersonalInfo();
        } catch (Exception e) {
            showError("Invalid balance!");
        }
    }

    public void editEmailField() {
        String newEmail = editFieldsView("email");
        try {
            manager.editField("email", newEmail);
            viewPersonalInfo();
        } catch (Exception e) {
            showError("Invalid email address!");
        }
    }

    public void editNumberField() {
        String newNumber = editFieldsView("phone number");
        try {
            manager.editField("number", newNumber);
            viewPersonalInfo();
        } catch (Exception e) {
            showError("Invalid phone number!");
        }
    }

    public String editFieldsView(String field) {
        Dialog<String> dialog = new Dialog<>();
        String updatedVersion;
        dialog.setTitle("Change Personal Information");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        if (field.equals("password")) {
            PasswordField passwordField = new PasswordField();
            HBox content = new HBox();
            content.setAlignment(Pos.CENTER_LEFT);
            content.setSpacing(10);
            content.getChildren().addAll(new Label("Enter your new password :"), passwordField);
            dialog.getDialogPane().setContent(content);
            dialog.showAndWait();
            updatedVersion = passwordField.getText();
        } else {
            TextField textField = new TextField();
            HBox content = new HBox();
            content.setAlignment(Pos.CENTER_LEFT);
            content.setSpacing(10);
            if (field.equals("balance")) {
                content.getChildren().addAll(new Label("Enter amount of money you want to add to you account :"), textField);
            } else {
                content.getChildren().addAll(new Label("Enter your new " + field + " :"), textField);
            }
            dialog.getDialogPane().setContent(content);
            dialog.showAndWait();
            updatedVersion = textField.getText();
        }
        return updatedVersion;
    }

    public void goToCartMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/CartMenu.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load(), 600, 600));
    }
}
