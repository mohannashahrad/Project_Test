package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Person {
    private ArrayList<BuyLog> buyHistory;
    private ArrayList<Discount> allDiscounts;
    private double amountOfAllPurchasing;
    private static ArrayList<Person> allCustomers = new ArrayList<>();

    public static ArrayList<Person> getAllCustomers() {
        return allCustomers;
    }

    public Customer(HashMap<String, String> information) {
        super(information);
        this.amountOfAllPurchasing = 0;
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

    public void addAmountOfAllPurchasing(Double amountToBeAdded){
        this.amountOfAllPurchasing += amountToBeAdded;
    }

    public void addToBuyLogs(BuyLog newBuyLog) {
        buyHistory.add(newBuyLog);
    }

    public double getAmountOfAllPurchasing() {
        return amountOfAllPurchasing;
    }

    public void setAmountOfAllPurchasing(double amountOfAllPurchasing) {
        this.amountOfAllPurchasing = amountOfAllPurchasing;
    }
}
