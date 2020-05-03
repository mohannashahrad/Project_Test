package View;

import model.Product;

public class ProductMenu extends Menu{
    Product product;
    public ProductMenu(Product product,Menu previousMenu) {
        super(product.getName(), previousMenu);
    }
    

    @Override
    public void commandProcess() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                digest();
            else if (command.equals("2"))
                attributes();
            else if (command.equals("3"))
                compare();
            else if (command.equals("4"))
                comments();
            else if (command.equals("5"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");

        }
        
    }

    private void comments() {
    }

    private void compare() {
    }

    private void attributes() {
    }

    private void digest() {
    }

    @Override
    public void show() {
        System.out.println(product.getName()+" Menu");
        System.out.println("1.digest\n2.attributes\n3.compare\n4.comments\n5.back");
    }
}
