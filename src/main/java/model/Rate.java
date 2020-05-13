package model;

import java.util.ArrayList;

public class Rate {
    private String user;
    private Product product;
    private double rate;
    private static ArrayList<Rate>allRates = new ArrayList<>();

    public static ArrayList<Rate> getAllRates() {
        return allRates;
    }

    public Rate(String user, Product product, double rate) {
        this.user = user;
        this.product = product;
        this.rate = rate;
    }

    public String getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public double getRate() {
        return rate;
    }
}
