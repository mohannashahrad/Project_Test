package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SellLog extends Log {
    private int sellCode;
    static private int lastSellCode = 0;
    private double receivedMoney;
    private double saleAmount;
    private Customer customer;
    private static ArrayList<Integer> allSellCodes = new ArrayList<>();
    private static ArrayList<Log>allSellLogs = new ArrayList<>();

    public static ArrayList<Log> getAllSellLogs() {
        return allSellLogs;
    }

    public SellLog(LocalDateTime date, double receivedMoney, double saleAmount, Customer customer) {
        super(date);
        this.sellCode = lastSellCode + 1;
        lastSellCode++;
        this.receivedMoney = receivedMoney;
        this.saleAmount = saleAmount;
        this.customer = customer;
        allSellCodes.add(sellCode);
    }

    public Customer getCustomer() {
        return customer;
    }

    public static ArrayList<Integer> getAllSellCodes() {
        return allSellCodes;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "sellCode=" + sellCode +
                ", receivedMoney=" + receivedMoney +
                ", saleAmount=" + saleAmount +
                ", customer=" + customer.getUsername() +
                '}';
    }
}