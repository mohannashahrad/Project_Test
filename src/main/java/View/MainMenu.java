package View;

import controller.Manager;
import controller.Storage;
import model.Filter;
import model.Sort;

public class MainMenu extends Menu {

    public MainMenu(Menu previousMenu) {
        super("MainMenu", previousMenu);
    }
    protected Filter filterName = new Filter("name","temp");
    protected Filter filterCategory = new Filter("category","temp");;
    protected Filter filterPrice = new Filter("price","temp");
    protected Sort sortAverageRate = new Sort("average rate");
    protected Sort sortPrice = new Sort("price");

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1")){
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            }
            else if (command.equals("2")){
                AccountMenu accountMenu = new AccountMenu("AccountMenu",this);
                accountMenu.run();
            }
            else if (command.equals("3")){
                AllProductsMenu allProductsMenu = new AllProductsMenu(this);
                allProductsMenu.run();
            }
            else if (command.equals("4")){
                OffsMenu offsMenu = new OffsMenu(this);
                offsMenu.run();
            }
            else if (command.equals("5")){
                CartMenu cartMenu = new CartMenu(this);
                cartMenu.run();
            }
            else if (command.equals("6")){
                manager.terminate();
                System.exit(0);
            }

            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }while (true);

    }

    @Override
    public void show() {
        System.out.println("Main Menu :");
        System.out.println("Enter\n1.Login and Register\n2.Account\n3.All Products\n4.Offs\n5.Cart\n6.Quit Program");
    }
}
