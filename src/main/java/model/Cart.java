package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {
    private HashMap<Product, Integer> productsInCart;
    private double totalPrice;
    private Customer customer;

    public Cart(Customer customer) {
        this.productsInCart = new HashMap<>();
        this.customer = customer;
    }

    public HashMap<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double calculateTotalPrice() {
        for(Product product : productsInCart.keySet()){
            totalPrice += product.getPrice() * productsInCart.get(product);
        }
        return totalPrice;
    }

    public void addProductToCart(Product newProduct) {
        productsInCart.put(newProduct, 1);
        newProduct.setSupply(newProduct.getSupply() - 1);
    }

    public void addNumberOfProductInTheCart(Product product){
        productsInCart.replace(product, productsInCart.get(product) + 1);
        product.setSupply(product.getSupply() - 1);
    }

    public void decreaseProduct(Product specificProduct) {
        if(productsInCart.get(specificProduct) != 0) {
            productsInCart.replace(specificProduct, productsInCart.get(specificProduct) - 1);
            specificProduct.setSupply(specificProduct.getSupply() + 1);
        }
        if(productsInCart.get(specificProduct) == 0){
            removeProduct(specificProduct);
        }
    }

    public void removeProduct(Product specificProduct) {
        productsInCart.remove(specificProduct, 0);
    }
}
