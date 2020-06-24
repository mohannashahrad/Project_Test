package graphics;

import controller.SellerManager;
import controller.Storage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Category;
import model.Person;
import model.Seller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SellerMenu extends Menu implements Initializable {
    SellerManager sellerManager = new SellerManager();
    Storage storage = new Storage();
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

    public SellerMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/SellerMenu.fxml");
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
        roleLabel.setText("seller");
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
            sellerManager.addBalance(Double.parseDouble(newBalance));
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

    public void viewCategories() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("All Categories");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH);
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setMinSize(300, 100);
        ArrayList<Category> categories;
        categories = storage.getAllCategories();
        if (categories.isEmpty()) {
            content.getChildren().addAll(new Label("There's not any categories yet!"));
        } else {
            for (int i = 0; i < categories.size(); i++) {
                content.getChildren().addAll(new Label("CATEGORY NUMBER " + (i + 1)));
                content.getChildren().addAll(new Label(categories.get(i).getCategoryName()));
                content.getChildren().addAll(new Label("This category's attributes are as followed :"));
                for (String property : categories.get(i).getProperties().keySet()) {
                    content.getChildren().add(new Label(property + " : " + categories.get(i).getProperties().get(property)));
                }
            }
        }
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
    }

    public void goToSellLogPage() throws IOException {
        ThisSellerSellLogs thisSellerSellLogs = new ThisSellerSellLogs(this);
        thisSellerSellLogs.run();
        /*FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/ThisPersonBuyLogs.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load(), 600, 600));*/
    }

    public void goToMainMenu() throws IOException {
        MainMenu mainMenu = new MainMenu(this);
        mainMenu.run();
        /*FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/MainMenu.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load(), 600, 600));*/
    }

    public void logout(ActionEvent actionEvent) {
        MainMenu mainMenu = new MainMenu(null);
        person = null;
        mainMenu.run();
    }
}
