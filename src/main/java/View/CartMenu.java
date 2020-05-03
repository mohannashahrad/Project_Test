package View;

public class CartMenu extends Menu{
    public CartMenu(Menu previousMenu) {
        super("CartMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showProducts();
            else if (command.equals("2"))
                viewSingleProduct();
            else if (command.equals("3"))
                increaseProduct();
            else if (command.equals("4"))
                decreaseProduct();
            else if (command.equals("5"))
                showTotalPrice();
            else if (command.equals("6"))
                purchase();
            else if (command.equals("7"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }

    }

    private void purchase() {
    }

    private void showTotalPrice() {
    }

    private void decreaseProduct() {
    }

    private void increaseProduct() {
    }

    private void viewSingleProduct() {
    }

    private void showProducts() {
    }

    @Override
    public void show() {
        System.out.println("Cart Menu :");
        System.out.println("Enter\n1.show products\n2.view a product\n3.increase a product\n4.decrease a product" +
                "\n5.show total price\n6.purchase\n7.back");

    }
}
