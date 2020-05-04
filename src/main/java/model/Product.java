package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product implements Comparable<Product> {
    private int productId;
    private int lastProductId = 0;
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

    public Product(HashMap<String, String> information, Seller seller) {
        this.productId = lastProductId + 1;
        lastProductId++;
        this.name = information.get("name");
        this.brand = information.get("brand");
        this.price = Double.parseDouble(information.get("price"));
        this.seller = seller;
        this.supply = Integer.parseInt(information.get("supply"));
        this.categoryName = information.get("categoryName");
        this.explanation = information.get("explanation");
        this.averageRate = 0;
        this.comments = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.thisProductsBuyers = new ArrayList<>();
    }

    public int getProductId() {
        return productId;
    }

    public int getLastProductId() {
        return lastProductId;
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

    @Override
    public String toString() {
        return "Product :" +
                "productId :" + productId +"\n" +
                " -name :" + name + "\n" +
                " -brand :" + brand + "\n" +
                " -price :" + price +"\n" +
                " -supply :" + supply +"\n" +
                " -category :" + categoryName + "\n" +
                " -explanation :" + explanation + "\n" +
                " -average rate :" + averageRate + "\n" +
                " -seller's name :" + seller.getName() + " " + seller.getFamilyName() + "\n";
    }
}