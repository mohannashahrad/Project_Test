package graphics;

import controller.CustomerManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BuyLog;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class perBuyLog implements Initializable {
    private CustomerManager customerManager = new CustomerManager();
    private BuyLog buyLog;
    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML
    TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML TableColumn<Product, Number> totalPriceColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> idColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> imageColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> numberColumn = new TableColumn<>();

    public perBuyLog(BuyLog buyLog) {
        this.buyLog = buyLog;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final ObservableList<Product> data = FXCollections.observableArrayList(
                buyLog.getProducts().keySet()
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
                            int quantity = buyLog.getProducts().get(product);
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
}
