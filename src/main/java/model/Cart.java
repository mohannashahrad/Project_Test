package model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> productsInCart;
    private double totalPrice;
    private Customer customer;

    public Cart(Customer customer) {
        this.productsInCart = new ArrayList<>();
        this.customer = customer;
    }

    public ArrayList<Product> getProductsInCart() {
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
        for (Product product : productsInCart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void addProduct(Product newProduct) {
        productsInCart.add(newProduct);
        newProduct.setSupply(newProduct.getSupply() - 1);
    }

    public void removeProduct(Product specificProduct) {
        productsInCart.removeIf(product -> product.equals(specificProduct));
        /*if (productsInCart.removeIf(product -> product.equals(specificProduct))) {
            specificProduct.setSupply(specificProduct.getSupply() + 1);
        }*/
    }
}
