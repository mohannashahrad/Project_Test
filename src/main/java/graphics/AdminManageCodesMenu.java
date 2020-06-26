package graphics;

import controller.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Customer;
import model.Discount;
import model.Request;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminManageCodesMenu extends Menu implements Initializable {

    AdminManager adminManager = new AdminManager();

    @FXML
    TableView discountTable = new TableView();
    @FXML TableColumn<Discount, Integer> idColumn = new TableColumn<>();
    @FXML TableColumn<Discount, String> codeColumn = new TableColumn<>();
    @FXML TableColumn<Discount, String> beginDateColumn = new TableColumn<>();
    @FXML TableColumn<Discount, String> endDateColumn = new TableColumn<>();
    @FXML TableColumn<Discount, Double> maxAmountColumn = new TableColumn<>();
    @FXML TableColumn<Discount, Double> percentageColumn = new TableColumn<>();
    @FXML TableColumn<Discount, Integer> maxUsageColumn = new TableColumn<>();
    @FXML TableColumn<Discount, Void> viewMoreColumn = new TableColumn<>();

    public AdminManageCodesMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/AdminManageCodesMenu.fxml");
    }

    private void updateShownDiscounts(ArrayList<Discount> shownDiscounts){
        final ObservableList<Discount> data = FXCollections.observableArrayList(
                shownDiscounts
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        beginDateColumn.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        maxAmountColumn.setCellValueFactory(new PropertyValueFactory<>("maxAmount"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        maxUsageColumn.setCellValueFactory(new PropertyValueFactory<>("usagePerCustomer"));
        addViewMoreButtonToTable(this);
        discountTable.setItems(data);
    }

    private void addViewMoreButtonToTable(AdminManageCodesMenu menu) {
        Callback<TableColumn<Discount, Void>, TableCell<Discount, Void>> cellFactory =
                new Callback<TableColumn<Discount, Void>, TableCell<Discount, Void>>() {
                    @Override
                    public TableCell<Discount, Void> call(final TableColumn<Discount, Void> param) {
                        final TableCell<Discount, Void> cell = new TableCell<Discount, Void>() {

                            private final Button btn = new Button("Click");

                            {
                                btn.setOnAction((ActionEvent event) -> {
                                    Discount discount = getTableView().getItems().get(getIndex());
                                    viewMore(discount);
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

        viewMoreColumn.setCellFactory(cellFactory);

    }

    private void viewMore(Discount discount){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(" Customers Using Discount");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Label moreInfo = new Label();
        HashMap<String,Integer> details = new HashMap<>();
        for (Customer customer : discount.getCustomersWithThisDiscount().keySet()) {
            details.put(customer.getUsername(),discount.getCustomersWithThisDiscount().get(customer));
        }
        moreInfo.setText(details.toString());
        VBox content = new VBox();
        content.getChildren().addAll(moreInfo);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
    }

    @FXML
    private void addDiscount(){

    }

    @FXML
    private void removeDiscount(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Remove Discount Code");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField codeField = new TextField();
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter discount code :"), codeField);
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
        try {
            adminManager.removeDiscountCode(codeField.getText());
            updateShownDiscounts(adminManager.viewAllDiscountCodes());
        } catch (Exception e) {
            showError(e.getMessage(),20);
        }
    }

    @FXML
    private void editDiscount(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateShownDiscounts(adminManager.viewAllDiscountCodes());
    }
}
