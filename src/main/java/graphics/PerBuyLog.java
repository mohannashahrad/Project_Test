package graphics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class PerBuyLog implements Initializable {
    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML
    TableColumn<Product, String> nameOfProductColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, String> amountOfSaleColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Double> priceWithOffColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, String> quantityColumn = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setTable(TableView<Product> tableView) {

    }
}
