package graphics;

import controller.SearchingManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
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
    @FXML
    TableView productTable = new TableView();
    @FXML TableColumn<Product, Double> priceColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> categoryColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> nameColumn = new TableColumn<>();
    @FXML TableColumn<Product, Image> imageColumn = new TableColumn<>();
    @FXML TableColumn<Product, Double> averageRateColumn = new TableColumn<>();
    @FXML TableColumn<Product, String> sellerColumn = new TableColumn<>();
    @FXML TableColumn<Product, Void> buttonColumn = new TableColumn<>();

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
    private void performFilter(){
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(!priceTextField.getText().equals("")) {
            try {
                updatedProducts.addAll(searchingManager.performFilter("price" , priceTextField.getText()));
            } catch (Exception e) {
                showError(e.getMessage() , 20);
            }
        }
        if(!nameTextField.getText().equals("")){
            try {
                for (Product product : searchingManager.performFilter("name", nameTextField.getText())) {
                    if(!updatedProducts.contains(product))
                        updatedProducts.add(product);
                }
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
        if(!categoryChoiceBox.getValue().equals("Choose Category")){
            try {
                for (Product product : searchingManager.performFilter("name", nameTextField.getText())) {
                    if(!updatedProducts.contains(product))
                        updatedProducts.add(product);
                }
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
        updateShownProducts(updatedProducts);
    }

    @FXML
    private void disableFilter(){
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(!priceTextField.getText().equals("")){
            try {
                updatedProducts.addAll(searchingManager.disableFilter("price",priceTextField.getText()));
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
         if(!nameTextField.getText().equals("")){
            try {
                updatedProducts.addAll(searchingManager.disableFilter("name",nameTextField.getText()));
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
         if(!categoryChoiceBox.getValue().equals("Choose Category")){
            try {
                updatedProducts.addAll(searchingManager.disableFilter("category",categoryChoiceBox.getValue().toString()));
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
        categoryChoiceBox.setValue("Choose Category");
        priceTextField.clear();
        nameTextField.clear();
        updateShownProducts(updatedProducts);
    }

    @FXML
    private void performSort() {
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(priceCheckBox.isSelected()) {
            try {
                updatedProducts.addAll(searchingManager.performSort("price"));
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
        if(averageRateCheckBox.isSelected()) {
            try {
                for (Product product : searchingManager.performSort("average rate")) {
                    if(!updatedProducts.contains(product))
                        updatedProducts.add(product);
                }
            } catch (Exception e) {
                showError(e.getMessage(),20);
            }
        }
        updateShownProducts(updatedProducts);
    }

    @FXML
    private void disableSort() throws Exception{
        ArrayList<Product> updatedProducts = new ArrayList<>();
        if(priceCheckBox.isSelected()){
            priceCheckBox.setSelected(false);
            updatedProducts.addAll(searchingManager.disableSort("price"));
        }
        if(averageRateCheckBox.isSelected()){
            priceCheckBox.setSelected(false);
           updatedProducts.addAll(searchingManager.disableSort("average rate"));
        }
        updateShownProducts(updatedProducts);
    }

    private void updateShownProducts(ArrayList<Product> shownProducts){
         final ObservableList<Product> data = FXCollections.observableArrayList(
                shownProducts
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        imageColumn.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);


            //Set up the Table
            TableCell<Product, Image> cell = new TableCell<Product, Image>() {
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item);
                    }
                }
            };
            // Attach the imageView to the cell
            cell.setGraphic(imageview);
            return cell;
        });
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("Image"));
        averageRateColumn.setCellValueFactory(new PropertyValueFactory<>("averageRate"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory<>("SellerName"));
        addButtonToTable(this);
        productTable.setItems(data);
    }

    private void addButtonToTable(AllProductsMenu menu) {
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory =
                new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {

                    private final Button btn = new Button("Click");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Product product = getTableView().getItems().get(getIndex());
                            ProductMenu productMenu = new ProductMenu(menu,product);
                            productMenu.run();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        buttonColumn.setCellFactory(cellFactory);

    }

    @FXML
    private void viewCart(){
        CartMenu cartMenu = new CartMenu(this);
        cartMenu.run();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populatingChoiceBoxes();
        updateShownProducts(searchingManager.viewAllProducts());
    }
}
