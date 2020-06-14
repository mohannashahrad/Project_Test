package graphics;

import controller.PurchasingManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class PurchaseMenu extends Menu {
    private PurchasingManager purchasingManager = new PurchasingManager();
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField number;
    @FXML
    private TextField discountCodeField;

    private void checkName() {
        if (name.getText().matches("")) {
            showError("This field is essential!");
        }
    }

    private void checkAddress() {
        if (address.getText().matches("")) {
            showError("This field is essential!");
        }
    }

    private void checkNumber() {
        if (number.getText().matches("")) {
            showError("This field is essential!");
        }
    }

    private void checkValidityOfDiscountCode() {
        String discountCode = discountCodeField.getText();
        if (!purchasingManager.doesCustomerHaveDiscountCode(discountCode)) {
            showError("Sorry!You don't have this discount!");
        }
        try {
            purchasingManager.checkDiscountValidity(discountCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkInputsValidity(){
        checkName();
        checkAddress();
        checkNumber();
        checkValidityOfDiscountCode();
    }
    public void goToPreviousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/CustomerMenu.fxml").toURI().toURL());
        Parent mainCallWindowFXML = loader.load();
        stage.setScene(new Scene(mainCallWindowFXML,600,600));
    }
}
