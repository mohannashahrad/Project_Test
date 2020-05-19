package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Person {
    private transient ArrayList<BuyLog> buyHistory;
    private transient ArrayList<Discount> allDiscounts;
    private double amountOfAllPurchasing;
    private static ArrayList<Person> allCustomers = new ArrayList<>();

    public static ArrayList<Person> getAllCustomers() {
        return allCustomers;
    }

    public Customer(HashMap<String, String> information) {
        super(information);
        this.buyHistory = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
    }

    public ArrayList<BuyLog> getBuyHistory() {
        return buyHistory;
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public void addToAllDiscounts(Discount newDiscount) {
        this.allDiscounts.add(newDiscount);
    }

    public void addToBuyLogs(BuyLog newBuyLog) {
        buyHistory.add(newBuyLog);
    }
}
