package model;

import java.util.ArrayList;

public class Comment {
    private String user;
    private Product product;
    private String commentTitle;
    private String commentBody;
    private static ArrayList<Comment>allComents = new ArrayList<>();

    public static ArrayList<Comment> getAllComents() {
        return allComents;
    }

    public Comment(String user, Product product, String commentTitle, String commentBody) {
        this.user = user;
        this.product = product;
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
    }

    public String getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public String getCommentBody() {
        return commentBody;
    }
}
