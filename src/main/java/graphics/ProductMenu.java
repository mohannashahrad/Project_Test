package graphics;

import controller.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Product;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenu extends Menu implements Initializable {

    private Product product;
    private CustomerManager customerManager = new CustomerManager();
    private ProductManager productManager = new ProductManager();
    @FXML
    public Label productNameLabel;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public ImageView imageview;
    @FXML
    public ListView listView;

    public ProductMenu(Menu previousMenu , Product product) {
        super(previousMenu, "src/main/java/graphics/fxml/ProductMenu.fxml");
        this.product = product;
        System.out.println(product.getName());
    }

    public void button(){
        try {
            customerManager.increaseProduct(Integer.toString(this.product.getProductId()));
        } catch (Exception e){
            showError(e.getMessage(), 200);
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
                rate();
                return;
            case "More Options":
                showError("No Action Selected", 100);
        }
    }

    private void rate(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Rate Product");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField rateField = new TextField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter a number between 0-5 :"), rateField);
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
        try {
            customerManager.rateProduct(product.getProductId(),Double.parseDouble(rateField.getText()));
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void compareAction() throws Exception {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Compare Product");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField compareField = new TextField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter second product's id :"), compareField);
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
        CompareMenu compareMenu = new CompareMenu(this , product.getProductId() , Integer.parseInt(compareField.getText()));
        compareMenu.run();
    }

    private void listViewContents() throws Exception{
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
        productNameLabel.setText(product.getName());
        imageview.setImage(new Image(product.getImagePath()));
        choiceBox.getItems().addAll("Compare", "Attributes" , "Comments" , "Rate" , "More Options");
        choiceBox.setValue("More Options");
        try {
            listViewContents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

