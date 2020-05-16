package View;

import controller.AdminManager;
import controller.SellerManager;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SellerMenu extends AccountMenu {
    SellerManager sellerManager;

    public SellerMenu(Menu previousMenu) {
        super("SellerMenu", previousMenu);
        sellerManager = new SellerManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewInfo();
            else if (command.equals("2"))
                viewCompanyInfo();
            else if (command.equals("3"))
                viewSalesHistory();
            else if (command.equals("4"))
                manageProductsMenu();
            else if (command.equals("5"))
                addProduct();
            else if (command.equals("6"))
                removeProduct();
            else if (command.equals("7"))
                showCategories();
            else if (command.equals("8"))
                viewOffsMenu();
            else if (command.equals("9"))
                viewBalance();
            else if (command.equals("10")) {
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            } else if (command.equals("11"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        } while (true);
        this.getPreviousMenu().run();

    }

    private void viewBalance() {
        System.out.println("Your balance is : " + person.getBalance());
    }

    private void viewOffsMenu() {
        ArrayList<Sale> allOffs = sellerManager.viewSellerOffs();
        if (allOffs.isEmpty()) {
            System.out.println("no offs yet.");
        } else {
            for (Sale off : allOffs) {
                System.out.println(off.getSaleId());
            }
        }
        while (true) {
            System.out.println("Enter\n1.view off\n2.edit off\n3.add off\n4.back");
            String command = scanner.nextLine();
            if (command.equals("1"))
                viewSingleOff();
            else if (command.equals("2"))
                editOff();
            else if (command.equals("3"))
                addOff();
            else if (command.equals("4"))
                break;
            else
                System.out.println("Invalid choice");

        }
    }

    private void viewSingleOff() {
        System.out.println("Enter offId :");
        int offId = Integer.parseInt(scanner.nextLine());
        try {
            Sale sale = sellerManager.viewSingleOff(offId);
            System.out.println(sale.getSaleId());
            System.out.println("Amount of sale : " + sale.getAmountOfSale());
            System.out.println("Begin Date : " + sale.getBeginDate());
            System.out.println("End Date : " + sale.getEndDate());
            System.out.println("List of products in this off : ");
            for (Product product : sale.getProductsWithThisSale()) {
                System.out.println(product.getProductId() + "------" + product.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addOff() {
        HashMap<String, String> information = new HashMap<>();
        ArrayList<Product> productsInThisSale = new ArrayList<>();
        int productId = 0;
       // scanner.nextLine();
        System.out.println("start date and time (yyyy,MM,dd,HH,mm) :");
        String startDate = scanner.nextLine().trim();
        information.put("beginDate", startDate);

        System.out.println("end date and time (yyyy,MM,dd,HH,mm) :");
        String endDate = scanner.nextLine().trim();
        information.put("endDate", endDate);
        try {
            System.out.println("Enter amount of sale :");
            information.put("amountOfSale", scanner.nextLine());
            System.out.println("Enter number of products in this off:");
            int numberOfProducts = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numberOfProducts; i++) {
                System.out.println("Enter the id of " + (i + 1) + "th product");
                productId = Integer.parseInt(scanner.nextLine());
                if (!sellerManager.doesProductExist(productId)) {
                    System.out.println("There is not a product with this Id! Try again :)");
                    i--;
                } else if (!sellerManager.doesSellerHaveProduct(productId)) {
                    System.out.println("You don't have this product in your available products! Try again :)");
                    i--;
                } else {
                    productsInThisSale.add(sellerManager.getSellerProductById(productId));
                }
            }
            sellerManager.addOff(information, productsInThisSale);
            System.out.println("created successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("unsuccessful creation!");
        }
    }

    private void editOff() {
        System.out.println("Enter Off Id :");
        int offId = Integer.parseInt(scanner.nextLine());
        if (!sellerManager.doesSellerHaveThisOff(offId)) {
            System.out.println("Invalid offId.");
            return;
        }
        while (true) {
            System.out.println("Enter field to edit :\n1.start date and time\n2.end date and time\n3.amount of sale\n4." +
                    "add product to off" + "\n5.remove product from off\n6.back");
            String command = scanner.nextLine();
            if (!(command.matches("[1-7]"))) {
                System.out.println("Invalid command");
                continue;
            } else if (command.equals("6"))
                break;
            System.out.println("Enter new value :");
            String newValue = scanner.nextLine();
            if (command.equals("1")) {
                try {
                    sellerManager.editOff(offId, "beginDate", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("2")) {
                try {
                    sellerManager.editOff(offId, "endDate", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("3")) {
                try {
                    sellerManager.editOff(offId, "amountOfSale", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("4")) {
                System.out.println("Enter productId : ");
                int productId = Integer.parseInt(scanner.nextLine());
                try {
                    sellerManager.addProductToOff(offId, productId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("5")) {
                System.out.println("Enter productId : ");
                int productId = Integer.parseInt(scanner.nextLine());
                try {
                    sellerManager.removeProductFromOff(offId, productId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void showCategories() {
        if (sellerManager.viewAllCategories().isEmpty()) {
            System.out.println("no categories yet.");
            return;
        }
        for (Category category : sellerManager.viewAllCategories()) {
            System.out.println(category.getCategoryName());
            System.out.println(category.getProperties());
            System.out.println("-------");
        }
    }

    private void removeProduct() {
        System.out.println("Enter product Id");
        String id = scanner.nextLine();
        try {
            sellerManager.removeProduct(Integer.parseInt(id));
            System.out.println("product removed successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProduct() {
        HashMap<String, String> information = new HashMap<>();
        try {
            System.out.println("Enter product name:");
            String productName = scanner.nextLine();
            information.put("name", productName);
            System.out.println("Enter brand name:");
            String brandName = scanner.nextLine();
            information.put("brand", brandName);
            System.out.println("Enter product price:");
            String price = scanner.nextLine();
            information.put("price", price);
            System.out.println("Enter product supply:");
            information.put("supply", scanner.nextLine());
            String category = categoryShow();
            information.put("categoryName", category);
            System.out.println("Enter product explanation:");
            information.put("explanation", scanner.nextLine());
            sellerManager.addProduct(information);
            System.out.println("created successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("unsuccessful creation!");
        }
    }

    private String categoryShow() {
        System.out.println("Enter product category:");
        ArrayList<Category> allCategories = sellerManager.viewAllCategories();
        int i = 1;
        for (Category category : allCategories) {
            System.out.println(i + ")" + category.getCategoryName());
            i++;
        }
        int command = Integer.parseInt(scanner.nextLine());
        if (command > allCategories.size()) {
            System.out.println("invalid choice.regarded as uncategorized.");
            return "uncategorized";
        }
        return allCategories.get(command - 1).getCategoryName();
    }

    private void manageProductsMenu() {
        showSellerProducts();
        while (true) {
            System.out.println("Enter\n1.view a product\n2.view buyers of a product\n3.edit a product\n4.back");
            String command = scanner.nextLine();
            if (command.equals("1")) {
                viewSingleProduct();
            } else if (command.equals("2")) {
                viewBuyers();
            } else if (command.equals("3")) {
                editProduct();
            } else if (command.equals("4"))
                break;
            else
                System.out.println("Invalid command");
        }
    }

    private void viewBuyers() {
        System.out.println("Enter productId:");
        int productId = Integer.parseInt(scanner.nextLine());
        try {
            ArrayList<Customer> thisProductBuyers = sellerManager.viewProductBuyers(productId);
            if (thisProductBuyers.isEmpty()) {
                System.out.println("No one bought this product yet!");
            }
            for (Customer buyer : thisProductBuyers) {
                System.out.println(buyer.getName() + " " + buyer.getFamilyName());///inja name o familyName kafie???
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void editProduct() {
        System.out.println("Enter productId:");
        int productId = Integer.parseInt(scanner.nextLine());
        if (!sellerManager.doesSellerHaveProduct(productId)) {
            System.out.println("Invalid productId.");
            return;
        }
        while (true) {
            System.out.println("Enter field to edit :\n1.name\n2.brand\n3.price\n4." +
                    "supply" + "\n5.category\n6.explanation\n7.back");
            String command = scanner.nextLine().trim();
            if (!(command.matches("[1-7]"))) {
                System.out.println("Invalid command");
                continue;
            } else if (command.equals("7"))
                break;
            else if (command.equals("5")) {
                String category = categoryShow();
                try {
                    sellerManager.editProduct(productId, "category", category);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            System.out.println("Enter new value :");
            String newValue = scanner.nextLine();
            if (command.equals("1")) {
                try {
                    sellerManager.editProduct(productId, "name", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("2")) {
                try {
                    sellerManager.editProduct(productId, "brand", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("3")) {
                try {
                    sellerManager.editProduct(productId, "price", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("4")) {
                try {
                    sellerManager.editProduct(productId, "supply", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("5")) {
                try {
                    sellerManager.editProduct(productId, "explanation", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    private void viewSingleProduct() {
        System.out.println("Enter productId:");
        int productId = scanner.nextInt();
        try {
            Product product = sellerManager.viewSellerProduct(productId);
            System.out.println("productId :" + product.getProductId() +
                    "\nProduct's name : " + product.getName() +
                    "\nProduct's brand : " + product.getBrand() +
                    "\nProduct's price : " + product.getPrice() +
                    "\nProduct's supply : " + product.getSupply() +
                    "\nProduct's category : " + product.getCategory().getCategoryName() +
                    "\nProduct's explanation : " + product.getExplanation() +
                    "\nProduct's average rate : " + product.getAverageRate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showSellerProducts() {
        if (sellerManager.viewSellerProducts().isEmpty()) {
            System.out.println("you dont have any products yet.");
            return;
        }
        System.out.println(sellerManager.viewSellerProducts());
    }

    private void viewSalesHistory() {
        if (sellerManager.viewSellerOffs().isEmpty()) {
            System.out.println("no sales yet.");
            return;
        }
        System.out.println(sellerManager.viewSellerOffs());
    }

    private void viewCompanyInfo() {
        System.out.println("company name : " + ((Seller) person).getCompany());
        System.out.println("Enter\n1.edit\n2.back");
        String command = scanner.nextLine();
        if (command.equals("1")) {
            System.out.println("Enter new value");
            String newValue = scanner.nextLine();
            ((Seller) person).setCompany(newValue);
        } else if (command.equals("2"))
            return;
        else
            System.out.println("Invalid choice");
    }

    private LocalDateTime dateMaker(String date) throws Exception {
        String[] stringDateArray = date.split(",");
        int[] intDateArray = new int[stringDateArray.length];
        for (int i = 0; i < stringDateArray.length; i++) intDateArray[i] = Integer.parseInt(stringDateArray[i]);
        LocalDateTime localDateTime = LocalDateTime.of(intDateArray[0], intDateArray[1], intDateArray[2], intDateArray[3], intDateArray[4]);
        return localDateTime;

    }

    @Override
    public void show() {
        System.out.println("Seller Menu :");
        System.out.println("1.view personal info\n2.view company information\n3.view sales history\n" +
                "4.manage products\n5.add product\n6.remove product\n7.show categories\n8.view offs\n" +
                "9.view balance\n10.Login and Register\n11.back");
    }
}
