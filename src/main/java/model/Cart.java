package model;


import controller.Storage;

import java.util.HashMap;

public class Cart {
    private static Cart cart;
    private HashMap<Product, Integer> productsInCart;
    private double totalPrice;
    private Customer customer;

    private Cart(Customer customer) {
        this.productsInCart = new HashMap<>();
        this.customer = customer;
        this.totalPrice = 0;
    }

    public static Cart getCart(){
        if (cart == null)
            cart = new Cart(null);
        return cart;
    }

    public HashMap<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(HashMap<Product, Integer> productsInCart) {
        this.productsInCart = productsInCart;
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
        for (Product product : productsInCart.keySet()) {
            totalPrice += (product.getPrice() * productsInCart.get(product));
        }
        return totalPrice;
    }

    public void addProductToCart(Product newProduct) {
            productsInCart.put(newProduct, 1);
            newProduct.setSupply(newProduct.getSupply() - 1);
            totalPrice += newProduct.getPrice();
    }

    public void addNumberOfProductInTheCart(Product product) {
        productsInCart.replace(product, productsInCart.get(product) + 1);
        product.setSupply(product.getSupply() - 1);
        totalPrice += product.getPrice();
    }

    public void decreaseProduct(Product specificProduct) {
        if (productsInCart.get(specificProduct) != 0) {
            productsInCart.replace(specificProduct, productsInCart.get(specificProduct) - 1);
            specificProduct.setSupply(specificProduct.getSupply() + 1);
            totalPrice -= specificProduct.getPrice();
        }
        if (productsInCart.get(specificProduct) == 0) {
            removeProduct(specificProduct);
        }
    }

    public void removeProduct(Product specificProduct) {
        productsInCart.remove(specificProduct, 0);
    }

    public void emptyCart(){
        this.totalPrice = 0;
        this.customer = null;
        this.productsInCart.clear();
    }
}
