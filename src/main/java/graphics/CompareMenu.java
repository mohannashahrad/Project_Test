package graphics;

import controller.ProductManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import java.net.URL;
import java.util.ResourceBundle;

public class CompareMenu extends Menu implements Initializable {
    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML TableColumn<Product, String> nameColumn = new TableColumn<>();
    @FXML TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> explanationColumn = new TableColumn<>();
    @FXML TableColumn<Product, Double> rateColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> brandColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> supplyColumn = new TableColumn<>();

    public CompareMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/CompareMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setTable(tableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setTable(TableView<Product> tableView) throws Exception {
        ProductManager productManager1 = new ProductManager();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        explanationColumn.setCellValueFactory(new PropertyValueFactory<>("explanation"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("averageRate"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        supplyColumn.setCellValueFactory(new PropertyValueFactory<>("supply"));
        final ObservableList<Product> data = FXCollections.observableArrayList(
                productManager1.getProductById(1),
                productManager1.getProductById(3)
        );
        tableView.setItems(data);
    }
}
