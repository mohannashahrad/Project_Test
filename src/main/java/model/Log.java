package model;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private Date date;
    private ArrayList<Product> products;

    public Log(Date date) {
        this.date = date;
        this.products = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProductToLog(Product product) {
        products.add(product);
    }
}
