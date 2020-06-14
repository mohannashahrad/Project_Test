package graphics;

import controller.PurchasingManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PurchasingMenu extends Menu {
    private PurchasingManager purchasingManager = new PurchasingManager();
    private HashMap<String, String> receivedInfo = new HashMap<>();
    private double finalPrice;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField number;
    @FXML
    private TextField discountCodeField;

    private boolean checkName() {
        if (name.getText().matches("")) {
            showError("All fields are essential except Discount Code!");
            return false;
        }
        return true;
    }

    private boolean checkAddress() {
        if (address.getText().matches("")) {
            showError("All fields are essential except Discount Code!");
            return false;
        }
        return true;
    }

    private boolean checkNumber() {
        if (number.getText().matches("")) {
            showError("All fields are essential except Discount Code!");
            return false;
        } else if (!number.getText().matches("\\d+")) {
            showError("Invalid phone number!");
            return false;
        }
        return true;
    }

    private boolean checkValidityOfDiscountCode() {
        String discountCode = discountCodeField.getText();
        if (discountCode != null) {
            if (!purchasingManager.doesCustomerHaveDiscountCode(discountCode)) {
                showError("Sorry!You don't have this discount!");
                return false;
            }
            try {
                purchasingManager.checkDiscountValidity(discountCode);
                finalPrice = purchasingManager.calculateTotalPriceWithDiscount(discountCode);
                return true;
            } catch (Exception e) {
                showError("You can't use this discount for one of following reasons:\n -This discount is expired!\n" +
                        " -This discount is not available yet!\n -You used this discount before and it's not available anymore!");
                return false;
            }
        } else {
            finalPrice = purchasingManager.getTotalPriceWithoutDiscount();
            return true;
        }
    }

    public void checkInputsValidity() throws Exception {
        if (!checkName()) {
            return;
        } else if (!checkAddress()) {
            return;
        } else if (!checkNumber()) {
            return;
        } else if (!checkValidityOfDiscountCode()) {
            return;
        }
        boolean nameValidity = checkName();
        boolean addressValidity = checkAddress();
        boolean numberValidity = checkNumber();
        boolean discountCodeValidity = checkValidityOfDiscountCode();
        if (nameValidity && addressValidity && numberValidity && discountCodeValidity) {
            if (person.getBalance() < finalPrice) {
                showError("Oops!You don't have enough money in your account!");
            } else {
                receivedInfo.put("receiverName", name.getText());
                receivedInfo.put("address", address.getText());
                receivedInfo.put("phoneNumber", name.getText());
                purchasingManager.performPayment(receivedInfo, finalPrice, purchasingManager.getDiscountPercentage(discountCodeField.getText()));
                showMessage();
            }
        }
    }

    public void goToPreviousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/graphics/fxml/CartMenu.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load(), 600, 600));
    }

    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Buying Message");
        alert.setContentText("Thanks for buying from us:)");
        alert.setHeaderText(null);
        alert.show();
    }
}
