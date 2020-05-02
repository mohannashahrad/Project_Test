package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Discount {
    private String discountCode;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int usagePerCustomer;
    private int percentage;
    private double maxAmount;
    private HashMap<Customer, Integer> customersWithThisDiscount;

    public Discount(String discountCode, LocalDateTime beginDate, LocalDateTime endDate, int percentage,
                    int usagePerCustomer, HashMap<Customer, Integer> customersWithThisDiscount, double maxAmount) {
        this.discountCode = discountCode;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.usagePerCustomer = usagePerCustomer;
        this.customersWithThisDiscount = customersWithThisDiscount;
        this.maxAmount = maxAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getUsageCount() {
        return usagePerCustomer;
    }

    public HashMap<Customer, Integer> getCustomersWithThisDiscount() {
        return customersWithThisDiscount;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setUsageCount(int usageCount) {
        this.usagePerCustomer = usageCount;
    }

    public void addCustomer(Customer newCustomer) {
        customersWithThisDiscount.put(newCustomer, 0);
    }

    public void removeCustomer(Customer specificCustomer, int usage) {
        customersWithThisDiscount.remove(specificCustomer, usage);
    }

    public void updateUsageOfDiscount(Customer specificCustomer) {
        this.customersWithThisDiscount.replace(specificCustomer, this.customersWithThisDiscount.get(specificCustomer) + 1);
    }

    public double calculateAmountOfDiscount(double totalPrice) {
        return totalPrice * ((double) percentage / 100);
    }
}
