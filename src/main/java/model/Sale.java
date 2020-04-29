package model;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private String saleId;
    private Date beginDate;
    private Date endDate;
    private double amountOfSale;
    private ArrayList<Product> productsWithThisSale;

    public Sale(String saleId, Date beginDate, Date endDate, double amountOfSale, ArrayList<Product> productsWithThisSale) {
        this.saleId = saleId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.amountOfSale = amountOfSale;
        this.productsWithThisSale = productsWithThisSale;
    }

    public String getSaleId() {
        return saleId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getAmountOfSale() {
        return amountOfSale;
    }

    public ArrayList<Product> getProductsWithThisSale() {
        return productsWithThisSale;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAmountOfSale(double amountOfSale) {
        this.amountOfSale = amountOfSale;
    }

    public double calculateAmountOfSale(double totalPrice) {
        return totalPrice * (this.amountOfSale);
    }
    public void addProductToThisSale(Product newProduct){
        productsWithThisSale.add(newProduct);
    }
    public void removeProductFromThisSale(Product specificProduct){
        productsWithThisSale.removeIf(product -> product.equals(specificProduct));
    }
}
