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

    public ProductMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/ProductMenu.fxml");
    }

    public void button(){
        try {
            customerManager.increaseProduct(Integer.toString(this.productId));
        } catch (Exception e){
            showError(e.getMessage());
        }
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
            case "Rate":
                System.out.println("Rate");
                return;
            case "More Options":
                //Here is a problem with stage which gives runtime Error
                showError("No Action Selected");
        }
    }

    private void compareAction() throws Exception {
        CompareMenu compareMenu = new CompareMenu(this);
        compareMenu.run();
    }

    private void listViewContents() throws Exception{
        Product product = productManager.getProductById(productId);
        String id = Integer.toString(product.getProductId());
        String name = product.getName();
        String brand = product.getBrand();
        String price = Double.toString(product.getPrice());
        String supply = Integer.toString(product.getSupply());
        String category = product.getCategory().getCategoryName();
        String rate = Double.toString(product.getAverageRate());
        listView.getItems().addAll("productId    | " + id, "Name          | " + name , "Brand          | " + brand ,
                "Price           | " + price , "supply         | " + supply , "Category     | " + category ,
                "Average Rate | " + rate);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNameLabel.setText("p1");
        choiceBox.getItems().addAll("Compare", "Attributes" , "Comments" , "Rate" , "More Options");
        choiceBox.setValue("More Options");
        try {
            listViewContents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

