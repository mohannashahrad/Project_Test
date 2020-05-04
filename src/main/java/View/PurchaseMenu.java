package View;

import controller.PurchasingManager;

import java.util.HashMap;

public class PurchaseMenu extends Menu {
    PurchasingManager purchasingManager;
    private HashMap<String,String> receivedInfo;
    private boolean menuSuccess;
    public PurchaseMenu(Menu previousMenu) {
        super("PurchaseMenu", previousMenu);
        receivedInfo = new HashMap<>();
        menuSuccess = false;
    }

    @Override
    public void commandProcess() {
        new Menu("ReceiveInfoMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
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
        if (menuSuccess == false)
            return;
        new Menu("DiscountCodeMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
                System.out.println("Do you want to use a discount code ?\n1.yes\n2.no\n3.cancel");
                int choice = scanner.nextInt();
                if (choice == 3){
                    return;
                } else if (choice == 2){
                    menuSuccess = true;
                } else if (choice == 1){
                    System.out.println("Please enter your discount code!");
                    String discountCode = scanner.nextLine();
                    if (discountCode.equalsIgnoreCase("cancel")){
                        return;
                    }
                    try {
                        purchasingManager.checkDiscountValidity(discountCode);
                        System.out.println("Your discount code is valid and you can use it :)");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    menuSuccess = true;
                    System.out.println("Discount code validation finished successfully!");
                }

            }

            @Override
            public void show() {
                System.out.println("Discount code validation Menu :");
                System.out.println("Enter 'cancel' at each step to cancel purchase.");
            }
        }.run();
        if (menuSuccess == false)
            return;
        new Menu("PaymentMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {
                System.out.println("This is your final receipt!");
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
        System.out.print("Purchase Menu :");
    }
}
