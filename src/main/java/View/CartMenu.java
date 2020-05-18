package View;

import controller.CustomerManager;
import model.Customer;
import model.Product;

public class CartMenu extends Menu{
    private CustomerManager customerManager;
    public CartMenu(Menu previousMenu) {
        super("CartMenu", previousMenu);
        customerManager = new CustomerManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showProducts();
            else if (command.equals("2"))
                viewSingleProduct();
            else if (command.equals("3"))
                increaseProduct();
            else if (command.equals("4"))
                decreaseProduct();
            else if (command.equals("5"))
                showTotalPrice();
            else if (command.equals("6"))
                purchase();
            else if (command.equals("7")){
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            }
            else if (command.equals("8"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }while (true);
        this.getPreviousMenu().run();

    }

    private void purchase() {
        if (person instanceof Customer){
            if (customerManager.getProductsInCart().isEmpty()){
                System.out.println("Your cart is empty.nothing to purchase!");
                return;
            }
            PurchaseMenu purchaseMenu = new PurchaseMenu(this);
            purchaseMenu.run();
        }else{
            System.out.println("First login as customer then purchase.");
            LoginRegisterMenu loginRegisterMenu = new  LoginRegisterMenu(this);
            loginRegisterMenu.run();
        }

    }

    private void showTotalPrice() {
        System.out.println("Total Price : " + customerManager.getCartTotalPrice()+" $");
        System.out.println("Total Price With Sale : " + customerManager.getCartTotalPriceWithSale()+" $");
    }

    private void decreaseProduct() {
        System.out.println("Enter product id :");
        String id = scanner.nextLine();
        try {
            customerManager.decreaseProduct(id);
            System.out.println("Product decreased successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void increaseProduct() {
        System.out.println("Enter product id :");
        String id = scanner.nextLine();
        try {
            customerManager.increaseProduct(id);
            System.out.println("Product increased successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void viewSingleProduct() {
        System.out.println("Enter product id :");
        String id = scanner.nextLine();
        try {
            int productId = Integer.parseInt(id);
            Product product = customerManager.getProductInCart(productId);
            ProductMenu productMenu = new ProductMenu(product,this);
            productMenu.run();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showProducts() {
        if (customerManager.getProductsInCart().isEmpty()){
            System.out.println("nothing in cart yet!");
            return;
        }
        for (Product product : customerManager.getProductsInCart().keySet()) {
            System.out.println(product.getName() + "---" + product.getProductId() + "---" + product.getPrice());
            System.out.println("Numbers of this product in your cart : " + customerManager.getProductsInCart().get(product));
            System.out.println("---------------");
        }
    }

    @Override
    public void show() {
        System.out.println("Cart Menu :");
        System.out.println("Enter\n1.show products\n2.view a product\n3.increase a product\n4.decrease a product" +
                "\n5.show total price\n6.purchase\n7.Login and register\n8.back");

    }
}
