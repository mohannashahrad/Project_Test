package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private String saleId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int amountOfSale;
    private ArrayList<Product> productsWithThisSale;

    public Sale(String saleId, LocalDateTime beginDate, LocalDateTime endDate, int amountOfSale, ArrayList<Product> productsWithThisSale) {
        this.saleId = saleId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.amountOfSale = amountOfSale;
        this.productsWithThisSale = productsWithThisSale;
    }

    public String getSaleId() {
        return saleId;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getAmountOfSale() {
        return amountOfSale;
    }

    public ArrayList<Product> getProductsWithThisSale() {
        return productsWithThisSale;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setAmountOfSale(int amountOfSale) {
        this.amountOfSale = amountOfSale;
    }

    public double calculateAmountOfSale(double totalPrice) {
        return totalPrice * (((double)(this.amountOfSale)) / 100);
    }
    public void addProductToThisSale(Product newProduct){
        productsWithThisSale.add(newProduct);
    }
    public void removeProductFromThisSale(Product specificProduct){
        productsWithThisSale.removeIf(product -> product.equals(specificProduct));
    }
}
