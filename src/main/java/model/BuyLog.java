package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog extends Log {
    private int buyCode;
    static private int lastBuyCode = 0;
    private double paidMoney;
    private double discountAmount;
    private ArrayList<Seller> seller;
    private HashMap<String, String> customerInfo;
    private static ArrayList<Integer> allBuyCodes = new ArrayList<>();

    public BuyLog(LocalDateTime date, double paidMoney, double discountAmount, ArrayList<Seller> sellers, HashMap<String, String> customerInfo) {
        super(date);
        this.paidMoney = paidMoney;
        this.discountAmount = discountAmount;
        this.seller = sellers;
        this.customerInfo = customerInfo;
        this.buyCode = lastBuyCode + 1;
        lastBuyCode++;
        allBuyCodes.add(buyCode);
    }

    public int getBuyCode() {
        return buyCode;
    }

    public int getLastBuyCode() {
        return lastBuyCode;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public ArrayList<Seller> getSeller() {
        return seller;
    }

    public HashMap<String, String> getCustomerInfo() {
        return customerInfo;
    }

    public ArrayList<Integer> getAllBuyCodes() {
        return allBuyCodes;
    }
}
