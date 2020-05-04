package View;

public class MainMenu extends Menu {
    public MainMenu(Menu previousMenu) {
        super("MainMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        while (true) {
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
            else if (command.equals("6"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }

    }

    @Override
    public void show() {
        System.out.println("Main Menu :");
        System.out.println("Enter\n1.Login and Register\n2.Account\n3.All Products\n4.Offs\n5.Cart\n6.Quit Program");
    }
}
