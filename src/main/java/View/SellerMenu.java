package View;

import controller.SellerManager;
import model.Seller;

public class SellerMenu extends AccountMenu {
    SellerManager sellerManager;
    @Override
    public void commandProcess() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewInfo();
            else if (command.equals("2"))
                viewCompanyInfo();
            else if (command.equals("3"))
                viewSalesHistory(); //bahar
            else if (command.equals("4"))
                manageProductsMenu();//bahar
            else if (command.equals("5"))
                addProduct(); //bahar
            else if (command.equals("6"))
                removeProduct();
            else if (command.equals("7"))
                showCategories(); //mohanna
            else if (command.equals("8"))
                viewOffsMenu(); //mohanna
            else if (command.equals("9"))
                viewBalance();
            else if (command.equals("10"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }

    }

    private void viewBalance() {
        System.out.println("Your balance is : "+person.getBalance());
    }

    private void viewOffsMenu() {
        viewSellerOffs();
        while (true){
            System.out.println("Enter\n1.view off\n2.edit off\n3.add off\n4.back");
            int command = scanner.nextInt();
            //to be continued

        }
    }

    private void showCategories() {

    }

    private void removeProduct() {
        System.out.println("Enter product Id");
        String id = scanner.nextLine();
        try {
            sellerManager.removeProduct(id);
            System.out.println("product removed successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addProduct() {
    }

    private void manageProductsMenu() {
        showSellerProducts(); //just names
        while (true){
            System.out.println("Enter\n1.view a product\n2.view buyers of a product\n3.edit a product\n4.back :");
            int command = scanner.nextInt();
            //to be continued
        }

    }

    private void viewSalesHistory() {
    }

    private void viewCompanyInfo() {
        System.out.println(((Seller)person).getCompany());
        System.out.println("Enter\n1.edit\n2.back");
        int command = scanner.nextInt();
        if(command == 1){
            System.out.println("Enter new value");
            String newValue = scanner.nextLine();
            ((Seller)person).setCompany(newValue);
        }else if (command == 2)
            return;
        else
            System.out.println("Invalid choice");
    }

    @Override
    public void show() {
        System.out.println("Seller Menu :");
        System.out.println("1.view personal info\n2.view company information\n3.view sales history\n" +
                "4.manage products\n5.add product\n6.remove product\n7.show categories\n8.view offs\n" +
                "9.view balance\n10.back");
    }
}
