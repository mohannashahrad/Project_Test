package graphics;

import controller.AdminManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenu extends Menu implements Initializable {
    private AdminManager adminManager = new AdminManager();
    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label familyNameLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label numberLabel;
    @FXML
    public Label roleLabel;


    public AdminMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/AdminMenu.fxml");
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
        roleLabel.setText("admin");
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

            content.getChildren().addAll(new Label("Enter your new " + field + " :"), textField);

            dialog.getDialogPane().setContent(content);
            dialog.showAndWait();
            updatedVersion = textField.getText();
        }
        return updatedVersion;
    }


    public void goToManageProductsMenu(ActionEvent actionEvent) {
    }

    public void goToManageUsersMenu(ActionEvent actionEvent) {
    }

    public void goToManageCodesMenu(ActionEvent actionEvent) {
    }

    public void goToManageRequestsMenu(ActionEvent actionEvent) {
    }

    public void goToManageCategoyMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
        MainMenu mainMenu = new MainMenu(null);
        person = null;
        mainMenu.run();
    }
}
