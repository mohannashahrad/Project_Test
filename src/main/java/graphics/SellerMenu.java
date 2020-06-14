package graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Role;

import java.net.URL;
import java.util.ResourceBundle;

public class SellerMenu extends Menu implements Initializable {
    @FXML
    private ComboBox personalInfoBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> personalInfoFields = FXCollections.observableArrayList("password", "name",
                "family name", "email", "number", "balance");
        personalInfoBox.setItems(personalInfoFields);
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
}
