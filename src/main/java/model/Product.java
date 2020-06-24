package model;

import java.util.ArrayList;
import java.util.HashMap;
import controller.Storage;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.*;

public class Product implements Comparable<Product> {
    private Storage storage = new Storage();
    private Image image;
    private String imagePath;
    private int productId;
    private String name;
    private String brand;
    private double price;
    private int numberInCart;
    private transient Seller seller;
    private int supply;
    private transient Category category;
    private String categoryName;
    private String sellerName;
    private String explanation;
    private double averageRate;
    private transient ArrayList<Comment> comments;
    private transient ArrayList<Rate> rates;
    private transient ArrayList<Customer> thisProductsBuyers;
    private transient Sale sale;
    private int numberOfSeen;
    private static ArrayList<Product> allProducts = new ArrayList<>();

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public Product(HashMap<String, String> information, Seller seller) {
        this.image = new Image ("file:src/main/java/model/images/book.png");
        this.imagePath = "file:src/main/java/model/images/book.png";
        this.productId = idSetter();
        this.name = information.get("name");
        this.brand = information.get("brand");
        this.price = Double.parseDouble(information.get("price"));
        this.seller = seller;
        this.sellerName = seller.getName();
        this.categoryName = information.get("categoryName");
        this.supply = Integer.parseInt(information.get("supply"));
        this.category = storage.getCategoryByName(information.get("categoryName"));
        this.explanation = information.get("explanation");
        this.averageRate = 0;
        this.comments = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.thisProductsBuyers = new ArrayList<>();
        this.sale = null;
        this.numberOfSeen = 0;
        if (this.category != null)
            this.category.addProductToCategory(this);
    }

    public Image getImage() {
        return new Image(this.imagePath);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSellerName() {
        return sellerName;
    }

    private int idSetter() {
        if (allProducts.size() == 0) {
            return 1;
        }
        int max = 0;
        for (Product product : allProducts) {
            if (product.productId > max)
                max = product.productId;
        }
        return max + 1;
    }

    public ObservableValue priceProperty() {
        ObservableValue<Double> observablePrice = new ReadOnlyObjectWrapper<Double>(price);
        return observablePrice;
    }

    public ObservableValue numberInCartProperty() {
        ObservableValue<Integer> observableNum = new ReadOnlyObjectWrapper<Integer>(numberInCart);
        return observableNum;
    }

    public ObservableValue imageProperty() {
        ObservableValue<Image> observableImg = new ReadOnlyObjectWrapper<Image>(image);
        return observableImg;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getProductId() {
        return productId;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
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

    public Sale getSale() {
        return sale;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getSupply() {
        return supply;
    }

    public Category getCategory() {
        return category;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getNumberOfSeen() {
        return numberOfSeen;
    }

    public void setNumberOfSeen(int numberOfSeen) {
        this.numberOfSeen = numberOfSeen;
    }

    public double getPriceWithSale() {
        if (this.sale == null)
            return price;
        else
            return this.price - this.sale.calculateAmountOfSale(this.price);
    }

    public double getAmountOfSale() {
        if (sale == null)
            return 0;
        else
            return this.sale.calculateAmountOfSale(this.price);
    }

    public void calculateAverageRate() {
        this.averageRate = 0;
        for (Rate rate : this.rates) {
            this.averageRate += rate.getRate();
        }
        this.averageRate = this.averageRate / this.rates.size();
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
                "productId :" + productId + "\n" +
                " -name :" + name + "\n" +
                " -brand :" + brand + "\n" +
                " -price :" + price + "\n" +
                " -supply :" + supply + "\n" +
                " -category :" + category.getCategoryName() + "\n" +
                " -explanation :" + explanation + "\n" +
                " -average rate :" + averageRate + "\n" +
                " -seller's name :" + seller.getName() + " " + seller.getFamilyName() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        Product first = (Product) this;
        Product second = (Product) obj;
        return first.getProductId() == second.getProductId();
    }
}