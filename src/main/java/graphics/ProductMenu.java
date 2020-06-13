package graphics;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import model.Product;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenu extends Menu implements Initializable {
    private CustomerManager customerManager = new CustomerManager();
    private ProductManager productManager = new ProductManager();
    private int productId = 2;
    @FXML
    public Label productNameLabel;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public ImageView imageview;
    @FXML
    public ListView listView;


    public void button(){
        System.out.println(customerManager.doesProductExist(1));
        /*try {
            customerManager.increaseProduct(Integer.toString(this.productId));
        } catch (Exception e){
            showNotification(Alert.AlertType.ERROR, this.stage.getScene().getWindow(), "Error!", e.getMessage());
        }*/
    }

    public void choiceBoxAction() throws Exception {
        switch (choiceBox.getValue().toString()){
            case "Comments":
                System.out.println("Comments");
                return;
            case "Attributes":
                System.out.println("Attributes");
                return;
            case "Compare":
                compareAction();
                return;
            case "Digest":
                System.out.println("Digest");
                return;
            case "Rate":
                System.out.println("Rate");
                return;
            case "More Options":
                //Here is a problem with stage which gives runtime Error
                showNotification(Alert.AlertType.ERROR, this.stage.getScene().getWindow(), "Error!",
                        "No Action Selected");
        }
    }

    private void showNotification(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private void compareAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/CompareMenu.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load(),600,600));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNameLabel.setText("p1");
        choiceBox.getItems().addAll("Compare" , "Digest" , "Attributes" , "Comments" , "Rate" , "More Options");
        choiceBox.setValue("More Options");
    }

    public static class CompareMenu extends Menu implements Initializable{
        @FXML TableView tableView;
        @FXML TableColumn<Product, String> nameColumn;
        @FXML TableColumn<Product, Double> priceColumn;
        @FXML TableColumn<Product, String> explanationColumn;
        @FXML TableColumn<Product, Double> rateColumn;
        @FXML TableColumn<Product, String> brandColumn;
        @FXML TableColumn<Product, Integer> supplyColumn;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                setTable(tableView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setTable(TableView tableView) throws Exception{
            ProductManager productManager1 = new ProductManager();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            explanationColumn.setCellValueFactory(new PropertyValueFactory<>("explanation"));
            rateColumn.setCellValueFactory(new PropertyValueFactory<>("averageRate"));
            brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            supplyColumn.setCellValueFactory(new PropertyValueFactory<>("supply"));
             final ObservableList<Product> data = FXCollections.observableArrayList(
                    productManager1.getProductById(1),
                    productManager1.getProductById(2)
            );
            tableView.setItems(data);
        }
    }
}

