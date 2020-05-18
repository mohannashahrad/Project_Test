package View;

import controller.ProductManager;
import controller.SearchingManager;
import model.*;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class AllProductsMenu extends Menu {
    ProductManager productManager = new ProductManager();
    SearchingManager searchingManager = new SearchingManager();

    public AllProductsMenu(Menu previousMenu) {
        super("AllProductsMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewCategories();
            else if (command.equals("2"))
                filterMenu();
            else if (command.equals("3"))
                sortMenu();
            else if (command.equals("4"))
                showProducts();
            else if (command.equals("5"))
                showSingleProduct();
            else if (command.equals("6")){
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            }
            else if (command.equals("7"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }while (true);
        this.getPreviousMenu().run();
    }

    private void showSingleProduct() {
        System.out.println("Enter product id :");
        String id = scanner.nextLine();
        try {
            int productId = Integer.parseInt(id);
            Product product = manager.getProductById(productId);
            ProductMenu productMenu = new ProductMenu(product, this);
            productManager.addNumberOfSeen(productId);
            productMenu.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showProducts() {
        if (searchingManager.viewAllProducts().isEmpty()){
            System.out.println("no products in store yet!");
            return;
        }
        System.out.println("All products:\n");
        for (Product product : productManager.viewAllProducts()) {
            System.out.println("id : "+product.getProductId());
            System.out.println("product name : "+product.getName()+"\nprice : "+product.getPrice()+"$");
            System.out.println("----");
        }
    }

    private void sortMenu() {
        while (true) {
            System.out.println("Enter\n1.show available sorts\n2.sort\n3.show current sort\n4.disable sort\n5.back");
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showAvailableSorts();
            else if (command.equals("2"))
                performSorting();
            else if (command.equals("3"))
                showCurrentSorts();
            else if (command.equals("4"))
                disableSort();
            else if (command.equals("5"))
                break;
            else
                System.out.println("Invalid choice");

        }
    }

    private void showAvailableSorts() {
        if (Sort.getAllSorts().isEmpty()){
            System.out.println("no available sort!");
            return;
        }
        for (Sort sort : Sort.getAllSorts()) {
            System.out.println("Sort By : " + sort.getSortName());
            System.out.println("----");
        }
    }

    private void performSorting() {

        System.out.println("Enter\n1.Sort by average rate\n2.Sort by price");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            try {
                showFilteredProducts(searchingManager.performSort("average rate"));
                System.out.println("Sorting finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            try {
                showFilteredProducts(searchingManager.performSort("price"));
                System.out.println("Sorting finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void showCurrentSorts() {
        if (searchingManager.getCurrentSorts().isEmpty()){
            System.out.println("There is no sort selected yet!");
        }
        for (Sort currentSort : searchingManager.getCurrentSorts()) {
            System.out.println("Sort By : " + currentSort.getSortName());
            System.out.println("----");
        }
    }

    private void disableSort() {
        System.out.println("Enter\n1.Disable sort by average rate\n2.Disable sort by price");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            try {
                showFilteredProducts(searchingManager.disableSort("average rate"));
                System.out.println("Disabling sort finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            try {
                showFilteredProducts(searchingManager.disableSort("price"));
                System.out.println("Disabling sort finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void filterMenu() {
        while (true) {
            System.out.println("Enter\n1.show available filters\n2.filter\n3.show current filters\n4.disable filter\n5.back");
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showAvailableFilters();
            else if (command.equals("2"))
                performFiltering();
            else if (command.equals("3"))
                showCurrentFilters();
            else if (command.equals("4"))
                disableFilter();
            else if (command.equals("5"))
                break;
            else
                System.out.println("Invalid choice");

        }
    }

    private void showAvailableFilters() {
        if (Filter.getAllFilters().isEmpty()){
            System.out.println("no available filter!");
            return;
        }
        for (Filter filter : Filter.getAllFilters()) {
            System.out.println("Filter By : " + filter.getFilterName());
            System.out.println("----");
        }
    }

    private void performFiltering() {
        System.out.println("Enter\n1.Filter by category\n2.Filter by price\n3.Filter by name");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            try {
                System.out.println("Please enter the name of your desired category!");
                String categoryName = scanner.nextLine();
                showFilteredProducts(searchingManager.performFilter("category", categoryName));
                System.out.println("Filtering finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            try {
                System.out.println("Please enter the maximum amount of products' price!");
                showFilteredProducts(searchingManager.performFilter("price", scanner.nextLine()));
                System.out.println("Filtering finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("3")) {
            try {
                System.out.println("Please enter name of the product!");
                String name = scanner.nextLine();
                showFilteredProducts(searchingManager.performFilter("name", name));
                System.out.println("Filtering finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void showCurrentFilters() {
        if (searchingManager.getCurrentFilters().isEmpty()){
            System.out.println("There is no current filters!");
            return;
        }
        for (Filter currentFilter : searchingManager.getCurrentFilters()) {
            System.out.println("Filter By : " + currentFilter.getFilterName());
            System.out.println("----");
        }
    }

    private void showFilteredProducts(ArrayList<Product> filteredProducts) {
        if (filteredProducts.isEmpty()) {
            System.out.println("No products found!!");
        } else {
            for (Product product : filteredProducts) {
                System.out.println(product.toString());
                System.out.println("------");
            }
        }
    }

    private void disableFilter() {
        System.out.println("Enter\n1.Disable filter by category\n2.Disable filter by price\n3.Disable filter by name");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            try {
                System.out.println("Please enter the name of your desired category!");
                String categoryName = scanner.nextLine();
                showFilteredProducts(searchingManager.disableFilter("category", categoryName));
                System.out.println("Disabling filter finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            try {
                System.out.println("Please enter the maximum amount of products' price to be disabled!");
                double maxPrice = scanner.nextDouble();
                showFilteredProducts(searchingManager.disableFilter("price", Double.toString(maxPrice)));
                System.out.println("Disabling filter finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("3")) {
            try {
                System.out.println("Please enter name of the product to be disabled!");
                String name = scanner.nextLine();
                showFilteredProducts(searchingManager.disableFilter("name", name));
                System.out.println("Disabling filter finished Successfully!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void viewCategories() {
        if (productManager.viewAllCategories().isEmpty()) {
            System.out.println("no categories yet.");
            return;
        }
        int i =1;
        for (Category category : productManager.viewAllCategories()) {
            System.out.println(i+")"+category.getCategoryName());
            System.out.println(category.getProperties());
            System.out.println("-------");
            i++;
        }
    }

    @Override
    public void show() {
        System.out.println("All Products Menu :");
        System.out.println("1.view categories\n2.filtering\n3.sorting\n4.show products\n5.show single product\n6.Login and Register\n7.back");
    }
}
