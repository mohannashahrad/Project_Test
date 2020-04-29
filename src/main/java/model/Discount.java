package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountCode;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private double amountOfDiscount;
    private int usageCount;
    private int percentage;
    private ArrayList<Customer> customersWithThisDiscount;

    public Discount(String discountCode, LocalDateTime beginDate, LocalDateTime endDate, int percentage, double amountOfDiscount) {
        this.discountCode = discountCode;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.amountOfDiscount = amountOfDiscount;
        this.usageCount = 1;
        this.customersWithThisDiscount = new ArrayList<>();
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

    public double getAmountOfDiscount() {
        return amountOfDiscount;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public ArrayList<Customer> getCustomersWithThisDiscount() {
        return customersWithThisDiscount;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setAmountOfDiscount(double amountOfDiscount) {
        this.amountOfDiscount = amountOfDiscount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }
    public void addCustomer(Customer newCustomer){
        customersWithThisDiscount.add(newCustomer);
    }
    public void removeCustomer(Customer specificCustomer){
        customersWithThisDiscount.removeIf(customer -> customer.equals(specificCustomer));
    }
}
