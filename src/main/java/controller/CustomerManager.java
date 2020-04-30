package controller;
import model.*;

import java.util.ArrayList;

public class CustomerManager extends Manager {

    public CustomerManager() {
    }

    public Cart viewCart (){
        return super.cart;
    }

    public ArrayList<Product> showProductsInCart(){
        return super.cart.getProductsInCart();
    }

    public Product viewProductInCart(String productId) throws Exception {
        if(!super.cart.getProductsInCart().contains(storage.getProductById(productId)))
            throw new Exception("You don't have such product in your cart!");
        else
            return storage.getProductById(productId);
    }

    public void increaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().contains(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.addProduct(storage.getProductById(productId));
    }

    public void decreaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().contains(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.removeProduct(storage.getProductById(productId));
    }

    public double getCartTotalPrice(){
        return super.cart.getTotalPrice();
    }

    
}
