package controller;

import model.Comment;
import model.*;

import java.util.ArrayList;

public class ProductManager extends Manager{

    public ProductManager() {
    }

    public ArrayList<Comment> showComments (int productId){
        return storage.getProductById(productId).getComments();
    }

    public void addComment (int productId ,String title ,String content){
        Product product = storage.getProductById(productId);
        String username = super.person.getUserName();
        Comment comment = new Comment(username,product,title,content);
        storage.addComment(comment);
        product.addComment(comment);
    }

    public void compareTwoProducts (int firstProduct , int secondProduct) throws Exception {
        if (storage.getProductById(secondProduct) == null)
            throw new Exception("There is not a product with this Id!");
        else if (firstProduct == secondProduct)
            throw new Exception("These products are the same!");
        else{
            Product first = storage.getProductById(firstProduct);
            Product second = storage.getProductById(secondProduct);
            System.out.println("First Product Name : " + first.getName() +
                    " --- Second Product Name : " + second.getName());
            System.out.println("First Product Price : " + first.getPrice() +
                    " --- Second Product Price : " + second.getPrice());
            System.out.println("First Product Seller Name : " + first.getSeller().getName() +
                    " --- Second Product Seller Name : " + second.getSeller().getName());
            System.out.println("First Product Average Rate : " + first.getAverageRate() +
                    " --- Second Product Average Rate : " + second.getAverageRate());
            System.out.println("First Product Brand : " + first.getBrand() +
                    " --- Second Product Brand : " + second.getBrand());
            System.out.println("First Product Explanation: " + first.getExplanation() +
                    " --- Second Product Explanation : " + second.getExplanation());
            System.out.println("First Product Number Of Available Samples : " + first.getSupply() +
                    " --- Second Product Number Of Available Samples : " + second.getSupply());
        }
    }
}
