package View;

import controller.ProductManager;
import model.Comment;
import model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductMenu extends Menu{
    Product product;
    ProductManager productManager = new ProductManager();
    public ProductMenu(Product product,Menu previousMenu) {
        super(product.getName(), previousMenu);
    }
    

    @Override
    public void commandProcess() {
        do {
            show();
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

        }while (true);
        
    }

    private void comments() {
        if (productManager.showComments(product.getProductId()).isEmpty()){
            System.out.println("No comments yet!");
        }
        else {
            for (Comment comment : productManager.showComments(product.getProductId())) {
                System.out.println("Title : " + comment.getCommentTitle());
                System.out.println("Content : " + comment.getCommentBody());
                System.out.println("-------");
            }
        }
        while (true) {
            System.out.println("Enter\n1.add comment\n2.back");
            int command = scanner.nextInt();
            if (command == 1)
                addComment();
            else if (command == 2)
                break;
            else
                System.out.println("Invalid choice");
        }
    }

    private void addComment(){
        System.out.println("Enter the title of your comment :");
        String title = scanner.nextLine();
        System.out.println("Enter the content of your comment :");
        String content = scanner.nextLine();
        productManager.addComment(product.getProductId(),title,content);
        System.out.println("Comment added successfully . Thank you for your feedback !");
    }

    private void compare() {
        System.out.println("Enter the second product's Id :");
        int secondProductId = scanner.nextInt();
        try {
            productManager.compareTwoProducts(product.getProductId(),secondProductId);
            System.out.println("Comparing finished Successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void attributes() {
        System.out.println("This product's attributes :\n");
        HashMap<String, String> attributes = productManager.viewAttributes(product.getCategoryName());
        for(String key : attributes.keySet()){
            System.out.println(key + " " + attributes.get(key) + "\n");
        }

    }

    private void digest() {
        System.out.println(product.toString());
    }

    @Override
    public void show() {
        System.out.println(product.getName()+" Menu");
        System.out.println("1.digest\n2.attributes\n3.compare\n4.comments\n5.back");
    }
}
