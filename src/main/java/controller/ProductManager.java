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
}
