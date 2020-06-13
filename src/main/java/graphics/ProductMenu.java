package graphics;

import controller.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenu extends Menu implements Initializable {
    private CustomerManager customerManager = new CustomerManager();
    private int productId;
    @FXML
    public Label productNameLabel;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public ImageView imageview;
    @FXML
    public ListView listView;


    public void button(){
        try {
            customerManager.increaseProduct(Integer.toString(this.productId));
        } catch (Exception e){
            showNotification(Alert.AlertType.ERROR, this.stage.getScene().getWindow(), "Error!", e.getMessage());
        }
    }

    public void choiceBoxAction(){
    }

    private void showNotification(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNameLabel.setText("p1");
        choiceBox.getItems().addAll("Compare" , "Digest" , "Attributes" , "Comments" , "Rate" , "More Options");
        choiceBox.setValue("More Options");
    }
}
