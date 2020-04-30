package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class SellLog extends Log {
    private int sellCode = 0;
    private double receivedMoney;
    private double saleAmount;
    private Customer customer;
    private static ArrayList<Integer> allSellCodes = new ArrayList<>();

    public SellLog(LocalDateTime date, double receivedMoney, double saleAmount, Customer customer) {
        super(date);
        this.receivedMoney = receivedMoney;
        this.saleAmount = saleAmount;
        this.customer = customer;
        sellCode = sellCode + 1;
        allSellCodes.add(sellCode);
    }

    public int getSellCode() {
        return sellCode;
    }

    public double getReceivedMoney() {
        return receivedMoney;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static ArrayList<Integer> getAllSellCodes() {
        return allSellCodes;
    }
}