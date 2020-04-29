package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog extends Log {
    private int buyCode = 0;
    private double paidMoney;
    private double discountAmount;
    private Seller seller;
    private HashMap<String, String> customerInfo;
    private static ArrayList<Integer> allBuyCodes = new ArrayList<>();

    public BuyLog(Date date, double paidMoney, double discountAmount, Seller seller, HashMap<String, String> customerInfo) {
        super(date);
        this.paidMoney = paidMoney;
        this.discountAmount = discountAmount;
        this.seller = seller;
        this.customerInfo = customerInfo;
        this.buyCode = buyCode + 1;
        allBuyCodes.add(buyCode);
    }

    public int getBuyCode() {
        return buyCode;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Seller getSeller() {
        return seller;
    }

    public HashMap<String, String> getCustomerInfo() {
        return customerInfo;
    }

    public ArrayList<Integer> getAllBuyCodes() {
        return allBuyCodes;
    }
}
