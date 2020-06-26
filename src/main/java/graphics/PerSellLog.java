package graphics;

import controller.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.SellLog;

import java.net.URL;
import java.util.ResourceBundle;

public class PerSellLog extends Menu implements Initializable {
    private SellLog sellLog;
    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML
    TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, String> productNameColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Integer> idColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Integer> numberColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Integer> brandName = new TableColumn<>();
    @FXML
    Label receivedMoney;
    @FXML
    Label amountOfSale;
    @FXML
    Label date;
    @FXML
    Label logCode;

    public PerSellLog(SellLog sellLog, Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/PerSellLog.fxml");
        this.sellLog = sellLog;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final ObservableList<Product> productsInSellLog = FXCollections.observableArrayList(
               sellLog.getProducts().keySet()
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>(sellLog.getProducts().values().toString()));
        tableView.setItems(productsInSellLog);
        receivedMoney.setText(Double.toString(sellLog.getReceivedMoney()));
        amountOfSale.setText(Double.toString(sellLog.getSaleAmount()));
        date.setText(sellLog.getDate().toString());
        logCode.setText(Integer.toString(sellLog.getSellCode()));
    }
}
