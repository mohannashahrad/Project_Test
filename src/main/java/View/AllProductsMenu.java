package View;

import model.Product;

public class AllProductsMenu extends Menu {
    public AllProductsMenu(Menu previousMenu) {
        super("AllProductsMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewCategories();//mohanna
            else if (command.equals("2"))
                filterMenu(); //mohanna
            else if (command.equals("3"))
                sortMenu(); //bahar
            else if (command.equals("4"))
                showProducts();//bahar
            else if (command.equals("5"))
                showSingleProduct();
            else if (command.equals("6"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }
    }

    private void showSingleProduct() {
        System.out.println("Enter product id :");
        String id = scanner.nextLine();
        try {
            int productId = Integer.parseInt(id);
            Product product = manager.getProductById(productId);
            ProductMenu productMenu = new ProductMenu(product,this);
            productMenu.run();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showProducts() {
    }

    private void sortMenu() {
    }

    private void filterMenu() {
    }

    private void viewCategories() {
    }

    @Override
    public void show() {
        System.out.println("All Products Menu :");
        System.out.println("1.view categories\n2.filtering\n3.sorting\n4.show products\n5.show single product\n6.back");
    }
}
