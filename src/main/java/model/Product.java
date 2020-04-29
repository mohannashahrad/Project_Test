package model;

import java.util.ArrayList;

public class Product implements Comparable<Product> {
    private String productId;
    private String name;
    private String brand;
    private double price;
    private Seller seller;
    private int supply;
    private String categoryName;
    private String explanation;
    private double averageRate;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;
    private ArrayList<Customer> thisProductsBuyers;

    public Product(String productId, String name, String brand, double price, Seller seller, int supply, String categoryName, String explanation) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.seller = seller;
        this.supply = supply;
        this.categoryName = categoryName;
        this.explanation = explanation;
        this.averageRate = 0;
        this.comments = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.thisProductsBuyers = new ArrayList<>();
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getSupply() {
        return supply;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getExplanation() {
        return explanation;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public ArrayList<Customer> getThisProductsBuyers() {
        return thisProductsBuyers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void calculateAverageRate() {
        this.averageRate = 0;
        for (Rate rate : rates) {
            averageRate += rate.getRate();
        }
        averageRate = averageRate / rates.size();
    }

    public void addComment(Comment newComment) {
        comments.add(newComment);
    }

    public void addBuyer(Customer newBuyer) {
        thisProductsBuyers.add(newBuyer);
    }

    public void addRate(Rate newRate) {
        rates.add(newRate);
    }

    @Override
    public int compareTo(Product productToBeComparedTo) {
        return Double.compare(this.getPrice(), productToBeComparedTo.getPrice());
    }
}