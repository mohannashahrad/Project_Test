package View;

import controller.ProductManager;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class OffsMenu extends Menu{
    ProductManager productManager;
    public OffsMenu(Menu previousMenu) {
        super("OffsMenu", previousMenu);
        productManager = new ProductManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            boolean saleExist = AllOffShow();
            if (!saleExist){
                System.out.println("no offs yet.");
                this.getPreviousMenu().run();
                break;
            }
            System.out.println("Enter\n1.Login and Register\n2.single product menu\n3.back");
            String command = scanner.nextLine().trim();
            if (command.equals("3"))
                break;
            else if (command.equals("1")){
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            }
            else if (command.equals("2")){
                System.out.println("Enter product id :");
                String id = scanner.nextLine();
                try {
                    Product product = manager.getProductById(Integer.parseInt(id));
                    ProductMenu productMenu = new ProductMenu(product,this);
                    productMenu.run();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        }while (true);
        
    }

    private boolean AllOffShow() {
        ArrayList<Product> offProducts = productManager.viewAllProductsWithSale();
        if (offProducts.isEmpty()){
            System.out.println("nothing in sale!");
            return false;
        }
        for (Product product : offProducts){
            System.out.println("name : "+product.getName());
            System.out.println("previous price : "+product.getPrice()+"$  ****  "+"current price : "+product.getPriceWithSale()+"$");
            System.out.println("amount of sale : "+product.getAmountOfSale()+"$");
        }
        return true;
    }


    @Override
    public void show() {
        System.out.println("Offs Menu :");
        System.out.println("Enter\n1.Login and Register\n2.single product menu\n3.back");

    }
}
