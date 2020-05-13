package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Log {
    protected LocalDateTime date;
    protected ArrayList<Product> products;
    private static ArrayList<Log>allLogs = new ArrayList<>();

    public static ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public Log(LocalDateTime date) {
        this.date = date;
        this.products = new ArrayList<>();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProductToLog(Product product) {
        products.add(product);
    }
}
