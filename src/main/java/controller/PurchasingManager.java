package controller;

import model.BuyLog;
import model.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PurchasingManager extends Manager{

    private int buyLogCode = 0;

    public PurchasingManager() {
    }

    public void performPayment(HashMap<String,String> receiverInformation , double totalPrice , double discountPercentage){
        double moneyToTransfer = totalPrice - totalPrice*(discountPercentage/100);
        person.setBalance(person.getBalance() - moneyToTransfer);
        createBuyLog(receiverInformation,totalPrice,discountPercentage);
        addCustomerToProductsBuyers();
        for (Seller seller : findDistinctSellers(super.cart)) {
            System.out.println(seller.getBalance());
            double totalPricePerSeller = calculateEachSellerMoneyTransfer(sellerProductsInCart(super.cart,seller));
            seller.addBalance(totalPricePerSeller);
            System.out.println(seller.getBalance());
            createSellLog(seller,receiverInformation,totalPricePerSeller,discountPercentage);
        }
        cart.emptyCart();
    }

    private void addCustomerToProductsBuyers(){
        for (Product product : cart.getProductsInCart().keySet()) {
            product.addBuyer((Customer)person);
        }
    }

    public ArrayList<Seller> findDistinctSellers (Cart cart){
        ArrayList<Seller> allSellers = new ArrayList<>();
        for (Product product : cart.getProductsInCart().keySet()) {
            if(!allSellers.contains(product.getSeller()))
                allSellers.add(product.getSeller());
        }
        return allSellers;
    }

    public void createBuyLog (HashMap<String,String> receiverInformation , double totalPrice , double saleAmount){
        ArrayList<Product> productsInCart = new ArrayList<>(cart.getProductsInCart().keySet());
        BuyLog buyLog = new BuyLog(LocalDateTime.now(),totalPrice,saleAmount,findDistinctSellers(super.cart),
                receiverInformation,productsInCart);
        storage.addBuyLog(buyLog);
        ((Customer)person).addToBuyLogs(buyLog);
        this.buyLogCode = buyLog.getBuyCode();
    }

    public int getBuyLogCode() {
        return buyLogCode;
    }

    public void createSellLog (Seller seller, HashMap<String,String> receiverInformation , double totalPrice , double saleAmount){
        SellLog sellLog = new SellLog(LocalDateTime.now(),totalPrice,saleAmount, (Customer) person);
        storage.addSellLog(sellLog);
        seller.addToSellLogs(sellLog);
    }

    public ArrayList<Product> sellerProductsInCart (Cart cart , Seller seller){
        ArrayList<Product> aimedProducts = new ArrayList<>();
        for (Product product : cart.getProductsInCart().keySet()) {
            if(product.getSeller().equals(seller))
                aimedProducts.add(product);
        }
        return aimedProducts;
    }

    public double calculateEachSellerMoneyTransfer (ArrayList<Product> products){
        double totalMoney = 0;
        for (Product product : products) {
            totalMoney += product.getPrice();
        }
        return totalMoney;
    }

    public void checkDiscountValidity(String discountCode) throws Exception {
        if (storage.getDiscountByCode(discountCode).getEndDate().isBefore(LocalDateTime.now()))
            throw new Exception("This discount is expired!");
        else if (storage.getDiscountByCode(discountCode).getBeginDate().isAfter(LocalDateTime.now()))
            throw new Exception("You can't use a discount which is not available yet!");
    }

    public boolean doesCustomerHaveEnoughMoney(double price){
        return !(person.getBalance() < price);
     }

    public double calculateTotalPriceWithDiscount (String discountCode){
        double totalPriceWithoutDiscount = getTotalPriceWithoutDiscount();
        double discountPercentage = storage.getDiscountByCode(discountCode).getPercentage();
        return (double)((100 - discountPercentage) * totalPriceWithoutDiscount)/100;
    }

    public double getDiscountPercentage (String discountCode){
        if (discountCode.equals("")){
            return 0.0;
        }
        return storage.getDiscountByCode(discountCode).getPercentage();
    }

    public ArrayList<Product> getProductsInCart (){
        ArrayList<Product> productsInCart = new ArrayList<>();
        productsInCart.addAll(super.cart.getProductsInCart().keySet());
        return productsInCart;
    }

    public String displayDetailsOfProduct (Product product) {
        String details = product.getName() + " -- " + product.getPrice() + " -- " + product.getAmountOfSale()
                + " -- " + product.getPriceWithSale() + " -- " + cart.getProductsInCart().get(product);
        return details;
    }

    public double getTotalPriceWithoutDiscount(){
        double totalPrice = 0;
        for (Product product : super.cart.getProductsInCart().keySet()) {
            totalPrice += (super.cart.getProductsInCart().get(product) * product.getPriceWithSale());
        }
        return totalPrice;
    }
}

