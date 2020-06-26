package model;


import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Log {
    protected LocalDateTime date;
    protected HashMap<Product, Integer> products;
    private static ArrayList<Log>allLogs = new ArrayList<>();

    public static ArrayList<Log> getAllLogs() {
        return allLogs;
    }
    @JsonCreator
    public Log(LocalDateTime date) {
        this.date = date;
        this.products = new HashMap<>();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object obj) {
        Log first = (Log) this;
        Log second = (Log) obj;
        boolean date = this.date.equals(((Log) obj).date);
        boolean products = this.products.equals(((Log) obj).products);
        return date && products;
    }
}
