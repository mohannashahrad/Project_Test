package model;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountCode;
    private Date beginDate;
    private Date endDate;
    private double amountOfDiscount;
    private int usageCount;
    private ArrayList<Customer> customersWithThisDiscount;

    public Discount(String discountCode, Date beginDate, Date endDate, double amountOfDiscount) {
        this.discountCode = discountCode;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.amountOfDiscount = amountOfDiscount;
        this.usageCount = 1;
        this.customersWithThisDiscount = new ArrayList<>();
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
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

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
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
