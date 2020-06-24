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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populatingChoiceBoxes();
        updateShownProducts(searchingManager.viewAllProducts());
    }
}
