package View;

import controller.CustomerManager;
import controller.Manager;
import model.*;

import java.util.ArrayList;

public class CustomerMenu extends AccountMenu {
    private CustomerManager customerManager;
    public CustomerMenu(Menu previousMenu) {
        super("CustomerMenu", previousMenu);
        customerManager = new CustomerManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewInfo();
            else if (command.equals("2"))
                viewCart();
            else if (command.equals("3"))
                purchase();
            else if (command.equals("4"))
                viewOrders();
            else if (command.equals("5"))
                viewBalance();
            else if (command.equals("6"))
                viewDiscountCodes();
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

    private void viewDiscountCodes() {
        if(((Customer)person).getAllDiscounts().isEmpty())
            System.out.println("You don't have any discount code!");
        else {
            System.out.println("Your discounts are as followed :");
            ArrayList<Discount> discountArrayList = ((Customer)person).getAllDiscounts();
            for (Discount discount : discountArrayList){
                System.out.println("discount code : "+discount.getDiscountCode()+"\npercentage : "+discount.getPercentage()+"%\n" +
                        "left usage times : "+discount.getUsageCount());
            }
        }
    }

    private void viewBalance() {
        System.out.println("Your balance is : "+person.getBalance());
        System.out.println("Enter\n1.add balance\n2.back");
        String  command = scanner.nextLine().trim();
        if (command.equals("1")){
            System.out.println("Enter Amount to add :");
            double amount = Double.parseDouble(scanner.nextLine());
            customerManager.addBalance(amount);
            System.out.println("added successfully.\nYour current balance is : "+person.getBalance());
        }else if (command.equals("2"))
            return;
        else
            System.out.println("Invalid choice");
    }

    private void viewOrders() {
        showAllOrders();
        while (true){
            System.out.println("Enter\n1.show order\n2.rate\n3.back");
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showSingleOrder();
            else if (command.equals("2"))
                rateProduct();
            else if (command.equals("3"))
                break;
            else
                System.out.println("Invalid choice");
        }
    }

    private void showAllOrders (){
        for (BuyLog buyLog : customerManager.getCustomerBuyLogs()) {
            System.out.println("Your Buy Order :" + buyLog.getBuyCode());
            System.out.println("Date : " + buyLog.getDate());
            System.out.println("Paid money : " + buyLog.getPaidMoney());
            System.out.println("Discount Amount : " + buyLog.getDiscountAmount());
            System.out.println("Products in this log : ");
            for (Product product : buyLog.getProducts()) {
                System.out.println("Name " +product.getName() + "--- productId :" + product.getProductId() +
                        "--- price : " + product.getPrice());
            }
        }
    }

    private void showSingleOrder (){
        System.out.println("Enter your orderId :");
        String orderId = scanner.nextLine();
        if (customerManager.getCustomerBuyLogs().isEmpty()){
            System.out.println("You didn't have this order!");
            return;
        }
        BuyLog buyLog = customerManager.getOrderWithId(orderId);
        System.out.println("Your Buy Order :" + buyLog.getBuyCode());
        System.out.println("Date : " + buyLog.getDate());
        System.out.println("Paid money : " + buyLog.getPaidMoney());
        System.out.println("Discount Amount : " + buyLog.getDiscountAmount());
        System.out.println("Products in this log : ");
        for (Product product : buyLog.getProducts()) {
            System.out.println("Name : " +product.getName() + "--- productId :" + product.getProductId() +
                    "--- price : " + product.getPrice());
        }
    }

    private void rateProduct(){
        System.out.println("Enter the productId :");
        int productId = Integer.parseInt(scanner.nextLine());
        if (!customerManager.doesProductExist(productId)){
            System.out.println("There is not such product");
            return;
        }
        System.out.println("Enter your rate for the product : [the number should be between 1 and 5]");
        double rate = Double.parseDouble(scanner.nextLine());
        try {
            customerManager.rateProduct(productId,rate);
            System.out.println("Thanks for your feedback !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void purchase() {
        if (customerManager.CartIsEmpty()){
            System.out.println("your cart is empty.nothing to purchase!");
            return;
        }
        PurchaseMenu purchaseMenu = new PurchaseMenu(this);
        purchaseMenu.run();
    }

    private void viewCart() {
        CartMenu cartMenu = new CartMenu(this);
        cartMenu.run();
    }

    @Override
    public void run() {
        if (!(person instanceof Customer) )
            MainMenu.getMainMenu().run();
        else super.run();

    }

    @Override
    public void show() {
        System.out.println("Customer Menu :");
        System.out.println("1.view personal info\n2.view cart\n3.purchase\n4.view orders\n5.view balance\n" +
                "6.view discount codes\n7.Login and Register\n8.back");
    }
}
