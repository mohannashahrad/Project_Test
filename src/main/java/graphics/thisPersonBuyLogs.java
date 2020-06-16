package graphics;

import controller.CustomerManager;
import controller.Storage;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BuyLog;
import model.Customer;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static graphics.Menu.person;

public class thisPersonBuyLogs implements Initializable {
    CustomerManager customerManager = new CustomerManager();
    Storage storage = new Storage();
    @FXML
    TableView<BuyLog> tableView = new TableView<>();
    @FXML
    TableColumn<BuyLog, String> buyLogCode= new TableColumn<>();
    @FXML
    TableColumn<BuyLog, Double> totalPriceColumn = new TableColumn<>();
    @FXML
    TableColumn<BuyLog, Double> amountOfDiscountColumn = new TableColumn<>();
    @FXML
    TableColumn<BuyLog, LocalDateTime> dateColumn = new TableColumn<>();
    @FXML
    TextField logCode;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable(tableView);
    }

    public void setTable(TableView<BuyLog> tableView) {
        buyLogCode.setCellValueFactory(new PropertyValueFactory<>("buyCode"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("paidMoney"));
        amountOfDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<BuyLog> thisPersonBuyLogs = FXCollections.observableArrayList(returnThisPersonBuyLogs());
        tableView.setItems(thisPersonBuyLogs);
    }
    public ArrayList<BuyLog> returnThisPersonBuyLogs(){
        return ((Customer) person).getBuyHistory();
    }

    public void back(ActionEvent actionEvent) {
    }
    public void showBuyLog() {
        if (!logCode.getText().equals("")) {
            showError("Please Enter a code!");
        } else if(!logCode.getText().matches("\\d+")){
            showError("Buy log code is an integer!");
        } else if (customerManager.getCustomerBuyLogs().isEmpty()) {
            showError("You have not purchase anything!");
        } else if (!customerManager.doesCustomerHasThisBuyLog(Integer.parseInt(logCode.getText()))) {
            showError("Oops!You don't have this buy log!");
        } else {
            showWantedBuyLog(logCode.getText());
        }
    }

    private void showWantedBuyLog(String code) {
        BuyLog thisBuyLog = storage.getBuyLogByCode(code);
        perBuyLog buyLog =new perBuyLog(thisBuyLog);
        //FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/CartMenu.fxml").toURI().toURL());
        //stage.setScene(new Scene(loader.load(), 600, 600));
    }

    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();
    }
}
