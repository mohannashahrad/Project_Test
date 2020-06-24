package graphics;

import controller.CustomerManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Customer;
import model.Product;

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
    @FXML TableColumn<Product, Image> imageColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> numberColumn = new TableColumn<>();
    @FXML Label totalPriceLabel = new Label();

    public CartMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/CartMenu.fxml");
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
            showError(e.getMessage());
        }
    }

    public void increaseProduct(){
        String id = productTextField.getText();
        try {
            customerManager.increaseProduct(id);
            updateTable();
        }catch (Exception e){
            showError(e.getMessage());
        }
    }

    private void updateTable(){
        final ObservableList<Product> data = FXCollections.observableArrayList(
                customerManager.getProductsInCart().keySet()
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberInCart"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imageColumn.setCellFactory(param -> new ImageTableCell<>());
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
        totalPriceLabel.setText("Total Price OF Cart : " + customerManager.getCartTotalPrice());
    }

    @FXML
    private void purchase() throws IOException {
        if (person instanceof Customer){
            if (customerManager.getProductsInCart().isEmpty()){
                showError("Your cart is empty. Nothing to purchase!");
                return;
            }
            PurchasingMenu purchasingMenu = new PurchasingMenu(this);
            purchasingMenu.run();
        } else{
            showError("First login as customer then purchase.");
            LoginMenu loginMenu = new LoginMenu(this);
            loginMenu.run();
        }
    }


    private class ImageTableCell<S> extends TableCell<S, Image> {
        final ImageView imageView = new ImageView();

        ImageTableCell() {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(Image item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                imageView.setImage(null);
                setText(null);
                setGraphic(null);
            }

            imageView.setImage(item);
            setGraphic(imageView);
        }
    }
}
