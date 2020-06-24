package graphics;

import javafx.event.ActionEvent;
import model.Admin;
import model.Seller;


public class MainMenu extends Menu {
    public MainMenu(Menu previousMenu) {
        super(previousMenu,"src/main/java/graphics/fxml/MainMenu.fxml");
    }

    public void goToSalesMenu(ActionEvent actionEvent) {
        AllOffsMenu salesMenu = new AllOffsMenu(this);
        salesMenu.run();
    }

    public void goToProductsMenu(ActionEvent actionEvent) {
        AllProductsMenu allProductsMenu = new AllProductsMenu(this);
        allProductsMenu.run();
    }

    public void goToAccount(ActionEvent actionEvent) {
        if (person == null) {
            LoginMenu loginMenu = new LoginMenu(this);
            loginMenu.run();
        } else if (person instanceof Admin) {
            AdminMenu adminMenu = new AdminMenu(this);
            adminMenu.run();
        } else if (person instanceof Seller) {
            SellerMenu sellerMenu = new SellerMenu(this);
            sellerMenu.run();
        } else {
            CustomerMenu customerMenu = new CustomerMenu(this);
            customerMenu.run();
        }
    }

}
