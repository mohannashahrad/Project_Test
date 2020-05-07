package controller;

import model.Comment;
import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductManager extends Manager {

    public ProductManager() {
    }

    public ArrayList<Comment> showComments(int productId) {
        return storage.getProductById(productId).getComments();
    }

    public void addComment(int productId, String title, String content) {
        HashMap<String,String> information = new HashMap<>();
        information.put("productId",Integer.toString(productId));
        information.put("title",title);
        information.put("content",content);
        information.put("username",person.getUsername());
        storage.addRequest(new Request("add comment",information));
    }

    public void compareTwoProducts(int firstProduct, int secondProduct) throws Exception {
        if (storage.getProductById(secondProduct) == null)
            throw new Exception("There is not a product with this Id!");
        else if (firstProduct == secondProduct)
            throw new Exception("These products are the same!");
        else {
            Product first = storage.getProductById(firstProduct);
            Product second = storage.getProductById(secondProduct);
            System.out.println("First Product Name : " + first.getName() +
                    " --- Second Product Name : " + second.getName());
            System.out.println("First Product Price : " + first.getPrice() +
                    " --- Second Product Price : " + second.getPrice());
            System.out.println("First Product Seller Name : " + first.getSeller().getName() + " " +
                    first.getSeller().getFamilyName() + " --- Second Product Seller Name : " +
                    second.getSeller().getName() + " " + second.getSeller().getFamilyName());
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

    public ArrayList<Product> viewAllProducts() {
        return storage.getAllProducts();
    }

    public HashMap<String, String> viewAttributes(String categoryName) {
        return storage.getCategoryByName(categoryName).getProperties();
    }

    public ArrayList<Product> viewAllProductsWithSale() {
        ArrayList<Product> finalProducts = new ArrayList<>();
        for (Product product : storage.getAllProducts()) {
            if (product.getSale() != null) {
                finalProducts.add(product);
            }
        }
        return finalProducts;
    }
}
