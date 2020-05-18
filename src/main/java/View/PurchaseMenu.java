package View;

import controller.PurchasingManager;
import model.Product;

import java.util.HashMap;

public class PurchaseMenu extends Menu {
    PurchasingManager purchasingManager;
    private HashMap<String,String> receivedInfo;
    private boolean menuSuccess;
    private String discount = "";
    private double finalTotalPrice;
    public PurchaseMenu(Menu previousMenu) {
        super("PurchaseMenu", previousMenu);
        receivedInfo = new HashMap<>();
        menuSuccess = false;
        discount = "";
        finalTotalPrice = 0;
        purchasingManager = new PurchasingManager();
    }

    @Override
    public void commandProcess() {
        show();
        new Menu("ReceiveInfoMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
                show();
                System.out.println("Address :");
                String info = scanner.nextLine();
                if (info.trim().equalsIgnoreCase("cancel"))
                    return;
                else
                    receivedInfo.put("address",info);

                System.out.println("Phone number :");
                info = scanner.nextLine();
                if (info.trim().equalsIgnoreCase("cancel"))
                    return;
                else
                    receivedInfo.put("phoneNumber",info);

                System.out.println("Receiver's name :");
                info = scanner.nextLine();
                if (info.trim().equalsIgnoreCase("cancel"))
                    return;
                else
                    receivedInfo.put("receiverName",info);

                menuSuccess = true;
                System.out.println("your information was received successfully!");

            }

            @Override
            public void show() {
                System.out.println("Receive Information Menu :");
                System.out.println("Enter required information in each step.");
                System.out.println("Enter 'cancel' at each step to cancel purchase.");
            }
        }.run();
        if (!menuSuccess)
            return;
        new Menu("DiscountCodeMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
                show();
                System.out.println("Do you want to use a discount code ?\n1.yes\n2.no\n3.cancel");
                String choice = scanner.nextLine();
                if (choice.equals("3")){
                    return;
                } else if (choice.equals("2")){
                    menuSuccess = true;
                } else if (choice.equals("1")){
                    System.out.println("Please enter your discount code!");
                    String discountCode = scanner.nextLine();
                    if (discountCode.equalsIgnoreCase("cancel")){
                        return;
                    }
                    if (!purchasingManager.doesCustomerHaveDiscountCode(discount)){
                        System.out.println("This discount was not assigned to you by the admin !");
                        return;
                    }
                    try {
                        purchasingManager.checkDiscountValidity(discountCode);
                        System.out.println("Your discount code is valid and you can use it :)");
                        discount = discountCode;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    menuSuccess = true;
                    System.out.println("Discount code validation finished successfully!");
                } else{
                    System.out.println("Invalid choice!");
                }
            }

            @Override
            public void show() {
                System.out.println("Discount code validation Menu :");
                System.out.println("Enter 'cancel' at each step to cancel purchase.");
            }
        }.run();
        if (!menuSuccess)
            return;
        new Menu("PaymentMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
                show();
                System.out.println("This is your final receipt!");
                System.out.println("In the following part the details of your cart will be displayed as below pattern!");
                System.out.println("Product's Name -- Product's Price -- Product's Amount Of Sale -- Product's Price With Sale" +
                        " -- Number Of Product In Cart");
                if (purchasingManager.getProductsInCart().isEmpty()){
                    System.out.println("There is no product in your cart!");
                }
                else {
                    for (Product product : purchasingManager.getProductsInCart()) {
                        System.out.println(purchasingManager.displayDetailsOfProduct(product));
                        System.out.println("---------");
                    }
                    System.out.println("Total Price without discount : " + purchasingManager.getTotalPriceWithoutDiscount());
                    if (discount.equalsIgnoreCase("")){
                        System.out.println("Amount of discount = 0\nFinal Total Price = " +
                                purchasingManager.getTotalPriceWithoutDiscount());
                        finalTotalPrice = purchasingManager.getTotalPriceWithoutDiscount();
                    }
                    else{
                        System.out.println("Amount of discount = " + purchasingManager.getDiscountPercentage(discount)
                                + "\nFinal Total Price = " + purchasingManager.calculateTotalPriceWithDiscount(discount));
                        finalTotalPrice = purchasingManager.calculateTotalPriceWithDiscount(discount);
                    }
                }
                System.out.println("Do you want to perform payment ?\n1.yes\n2.no\n3.cancel");
                String choice = scanner.nextLine().trim();
                if (choice.equals("3"))
                    return;
                else if (choice.equals("2"))
                    menuSuccess = true;
                else if (choice.equals("1")){
                    if (!purchasingManager.doesCustomerHaveEnoughMoney(finalTotalPrice)){
                        System.out.println("You don't have enough money in your account!");
                        menuSuccess = true;
                        return;
                    } else {
                        purchasingManager.performPayment(receivedInfo,finalTotalPrice,purchasingManager.getDiscountPercentage(discount));
                        if (!discount.equals("")){
                            purchasingManager.updateDiscountUsagePerPerson(discount);
                        }
                        System.out.println("Payment finished successfully!");
                        System.out.println("This is your buyLog code : " + purchasingManager.getBuyLogCode());
                        System.out.println("Thank you for buying from us :)");
                    }
                } else{
                    System.out.println("Invalid choice!");
                }
            }

            @Override
            public void show() {
                System.out.println("Payment Menu :");

            }
        }.run();
        this.getPreviousMenu().run();
    }

    @Override
    public void show() {
        System.out.print("Purchase Menu :\n");
    }

}
