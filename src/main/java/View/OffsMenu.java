package View;

import controller.ProductManager;
import model.Product;

import java.util.ArrayList;

public class OffsMenu extends Menu{
    ProductManager productManager;
    public OffsMenu(Menu previousMenu) {
        super("OffsMenu", previousMenu);
        ProductManager productManager = new ProductManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            AllOffShow();
            System.out.println("Enter product id to show or 'back' to return.");
            String command = scanner.nextLine();
            if (command.trim().equalsIgnoreCase("back"))
                break;
            else {
                try {
                    Product product = manager.getProductById(Integer.parseInt(command));
                    ProductMenu productMenu = new ProductMenu(product,this);
                    productMenu.run();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        }while (true);
        
    }

    private void AllOffShow() {
        ArrayList<Product> offProducts = productManager.viewAllProductsWithSale();
        for (Product product : offProducts){
            System.out.println("name : "+product.getName());
            System.out.println("previous price : "+product.getPrice()+"$  ****  "+"current price : "+product.getPriceWithSale()+"$");
            System.out.println("amount of sale : "+product.getAmountOfSale()+"$");
        }
    }


    @Override
    public void show() {
        System.out.println("Offs Menu");

    }
}
