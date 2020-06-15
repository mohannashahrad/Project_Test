package graphics;

import controller.CustomerManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Customer;
import model.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartMenu extends Menu implements Initializable {
    private CustomerManager customerManager = new CustomerManager();
    @FXML
    TextField productTextField;
    @FXML TableView<Product> tableView = new TableView<>();
    @FXML TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML TableColumn<Product, Number> totalPriceColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> idColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> imageColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> numberColumn = new TableColumn<>();

    public CartMenu(Menu previousMenu, String fxmlPath) {
        super(previousMenu, fxmlPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    public void decreaseProduct(){
        String id = productTextField.getText();
        try {
            customerManager.decreaseProduct(id);
            updateTable();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void increaseProduct(){
        String id = productTextField.getText();
        try {
            customerManager.increaseProduct(id);
            updateTable();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateTable(){
        final ObservableList<Product> data = FXCollections.observableArrayList(
                customerManager.getProductsInCart().keySet()
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberInCart"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        totalPriceColumn.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            return Bindings.createDoubleBinding(
                    () -> {
                        try {
                            double price = product.getPrice();
                            int quantity = customerManager.getProductsInCart().get(product);
                            return price * quantity ;
                        } catch (NumberFormatException nfe) {
                            return Double.valueOf(0);
                        }
                    },
                    product.priceProperty(),
                    product.numberInCartProperty()
            );
        });
        tableView.setItems(data);
    }

    @FXML
    private void purchase() throws IOException {
        if (person instanceof Customer){
            if (customerManager.getProductsInCart().isEmpty()){
                System.out.println("Your cart is empty. Nothing to purchase!");
                return;
            }
            System.out.println("Proceed to purchase ");
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/PurchasingMenu.fxml").toURI().toURL());
            stage.setScene(new Scene(loader.load(), 600, 600));
        } else{
            System.out.println("First login as customer then purchase.");
            FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/LoginMenu.fxml").toURI().toURL());
            stage.setScene(new Scene(loader.load(), 600, 600));
        }
    }

    @FXML
    private void back(){

    }
}
