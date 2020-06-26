package graphics;

import controller.AdminManager;
import controller.SellerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddDiscountPage extends Menu implements Initializable {

    AdminManager adminManager = new AdminManager();

    @FXML private TextField code = new TextField();
    @FXML private TextField percentage = new TextField();
    @FXML private TextField maxUsage = new TextField();
    @FXML private TextField maxAmount = new TextField();
    @FXML private DatePicker beginDate = new DatePicker();
    @FXML private DatePicker endDate = new DatePicker();

    public AddDiscountPage(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/AddDiscountPage.fxml");
    }

    @FXML
    private void addDiscount(){
        LocalDateTime begin = beginDate.getValue().atStartOfDay();
        LocalDateTime end = endDate.getValue().atStartOfDay();
        adminManager.createDiscountCode(code.getText(),begin,end,Integer.parseInt(percentage.getText()),
                Integer.parseInt(maxUsage.getText()),Double.parseDouble(maxAmount.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
