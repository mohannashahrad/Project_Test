package controller;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerManager extends Manager {

    public CustomerManager() {
    }

    public Cart viewCart (){
        return super.cart;
    }

    public HashMap<Product , Integer> showProductsInCart(){
        return super.cart.getProductsInCart();
    }

    public Product viewProductInCart(String productId) throws Exception {
        if(!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have such product in your cart!");
        else
            return storage.getProductById(productId);
    }

    public void increaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.addNumberOfProductInTheCart(storage.getProductById(productId));
    }

    public void decreaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.decreaseProduct(storage.getProductById(productId));
    }

    public double getCartTotalPrice(){
        return super.cart.getTotalPrice();
    }

    public void addProductToCart(String productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not a product with this Id!!");
        else if(super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You already have this product in you cart.Please use the increase[productId] command!");
        else
            super.cart.addProductToCart(storage.getProductById(productId));
    }

}
