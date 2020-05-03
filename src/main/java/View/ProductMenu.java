package View;

import controller.ProductManager;
import model.Comment;
import model.Product;

public class ProductMenu extends Menu{
    Product product;
    ProductManager productManager = new ProductManager();
    public ProductMenu(Product product,Menu previousMenu) {
        super(product.getName(), previousMenu);
    }
    

    @Override
    public void commandProcess() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                digest(); //bahar
            else if (command.equals("2"))
                attributes(); //bahar
            else if (command.equals("3"))
                compare(); //mohanna
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
