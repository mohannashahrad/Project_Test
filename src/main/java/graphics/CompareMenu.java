package graphics;

import controller.ProductManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class CompareMenu extends Menu implements Initializable {
    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML
    TableColumn<Product, String> nameColumn = new TableColumn<>();
    @FXML TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> explanationColumn = new TableColumn<>();
    @FXML TableColumn<Product, Double> rateColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> brandColumn = new TableColumn<>();
    @FXML TableColumn<Product, Integer> supplyColumn = new TableColumn<>();
    @FXML
    Button button = new Button();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setTable(tableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(){

    }

    public void setTable(TableView<Product> tableView) throws Exception {
        ProductManager productManager1 = new ProductManager();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        explanationColumn.setCellValueFactory(new PropertyValueFactory<>("Explanation"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("AverageRate"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        supplyColumn.setCellValueFactory(new PropertyValueFactory<>("Supply"));
        final ObservableList<Product> data = FXCollections.observableArrayList(
                productManager1.getProductById(1),
                productManager1.getProductById(3)
        );
        tableView.setItems(data);
        for (Product item : tableView.getItems()) {
            System.out.println(item.getName());
        }
    }
}
