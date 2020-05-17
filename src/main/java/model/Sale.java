package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private int saleId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int amountOfSale;
    private ArrayList<Product> productsWithThisSale;
    private static ArrayList<Sale> allSales = new ArrayList<>();

    private int idSetter() {
        if (allSales.size() == 0) {
            return 1;
        }
        int max = 0;
        for (Sale sale : allSales) {
            if (sale.saleId > max)
                max = sale.saleId;
        }
        return max + 1;
    }

    public static ArrayList<Sale> getAllSales() {
        return allSales;
    }

    public Sale(LocalDateTime beginDate, LocalDateTime endDate, int amountOfSale, ArrayList<Product> productsWithThisSale) {
        this.saleId = idSetter();
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.amountOfSale = amountOfSale;
        this.productsWithThisSale = productsWithThisSale;
    }

    public int getSaleId() {
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

    public int getLastSaleId() {
        return this.idSetter();
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
        return totalPrice * (((double) (this.amountOfSale)) / 100);
    }

    public void addProductToThisSale(Product newProduct) {
        productsWithThisSale.add(newProduct);
    }

    public void removeProductFromThisSale(Product specificProduct) {
        productsWithThisSale.removeIf(product -> product.equals(specificProduct));
    }

    public static void removeProductFromItSale(ArrayList<Sale> allSales, Product specificProduct) { //problem detected:deleting from ArrayList in a loop
        for (Sale sale : allSales) {
            if (sale != null && sale.getProductsWithThisSale().contains(specificProduct)) {
                sale.removeProductFromThisSale(specificProduct);
            }
        }
    }
}
