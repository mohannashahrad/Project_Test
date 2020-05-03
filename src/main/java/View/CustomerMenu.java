package View;

public class CustomerMenu extends AccountMenu {
    @Override
    public void commandProcess() {
        while (true) {
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
            else if (command.equals("7"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }

    }

    private void viewDiscountCodes() {
    }

    private void viewBalance() {
    }

    private void viewOrders() {
    }

    private void purchase() {
    }

    private void viewCart() {
    }

    @Override
    public void show() {
        System.out.println("Customer Menu :");
        System.out.println("1.view personal info\n2.view cart\n3.purchase\n4.view orders\n5.view balance\n" +
                "6.view discount codes\n7.back");
    }
}
