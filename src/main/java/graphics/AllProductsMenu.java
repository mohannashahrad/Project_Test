package graphics;

import controller.SearchingManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Category;
import model.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllProductsMenu extends Menu implements Initializable {

    private SearchingManager searchingManager = new SearchingManager();
    @FXML
    TextField priceTextField = new TextField();
    @FXML
    TextField nameTextField = new TextField();
    @FXML
    ChoiceBox categoryChoiceBox = new ChoiceBox();
    @FXML
    CheckBox averageRateCheckBox = new CheckBox();
    @FXML
    CheckBox priceCheckBox = new CheckBox();

    public AllProductsMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/AllProductsMenu.fxml");
    }

    @FXML
    private void populatingChoiceBoxes(){
        for (Category category : searchingManager.viewAllCategories()) {
            categoryChoiceBox.getItems().add(category.getCategoryName());
        }
        categoryChoiceBox.getItems().add("Choose Category");
        categoryChoiceBox.setValue("Choose Category");
    }

    @FXML
    private void performFilter() throws Exception{
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(!priceTextField.getText().equals("")) {
            updatedProducts.addAll(searchingManager.performFilter("price" , priceTextField.getText()));
        }
        if(!nameTextField.getText().equals("")){
            updatedProducts.addAll(searchingManager.performFilter("name" , nameTextField.getText()));
        }
        if(!categoryChoiceBox.getValue().equals("Choose Category")){
            updatedProducts.addAll(searchingManager.performFilter("category" , categoryChoiceBox.getValue().toString()));
        }
        updateShownProducts(updatedProducts);
    }

    @FXML
    private void disableFilter(){
        categoryChoiceBox.setValue("Choose Category");
        priceTextField.clear();
        nameTextField.clear();
        updateShownProducts(searchingManager.viewAllProducts());
    }

    @FXML
    private void performSort() throws Exception{
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(priceCheckBox.isSelected())
            updatedProducts.addAll(searchingManager.performSort("price"));
        if(averageRateCheckBox.isSelected())
            updatedProducts.addAll(searchingManager.performSort("average rate"));
        updateShownProducts(updatedProducts);
    }

    @FXML
    private void disableSort() throws Exception{
        if(priceCheckBox.isSelected()){
            priceCheckBox.setSelected(false);
            searchingManager.disableSort("price");
        }
        if(averageRateCheckBox.isSelected()){
            priceCheckBox.setSelected(false);
            searchingManager.disableSort("average rate");
        }
        updateShownProducts(searchingManager.viewAllProducts());
    }

    private void updateShownProducts(ArrayList<Product> shownProducts){
        System.out.println(searchingManager.viewAllProducts().toString());
        int x = 10 ;
        int y = 10 ;
        for (Product product : searchingManager.viewAllProducts()) {
            new ProductCell(x++,y++,3,3 , "file:src/main/java/graphics/leaf.jpg");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populatingChoiceBoxes();
        updateShownProducts(searchingManager.viewAllProducts());
    }
}
