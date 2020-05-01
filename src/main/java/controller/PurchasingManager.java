package controller;

import model.BuyLog;
import model.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PurchasingManager extends Manager{

    public PurchasingManager() {
    }

    public void performPayment(HashMap<String,String> receiverInformation , double totalPrice , double saleAmount){
        super.person.setBalance(super.person.getBalance() - totalPrice);
        createBuyLog(receiverInformation,totalPrice,saleAmount);
        for (Seller seller : findDistinctSellers(super.cart)) {
            double totalPricePerSeller = calculateEachSellerMoneyTransfer(sellerProductsInCart(super.cart,seller));
            createSellLog(seller,receiverInformation,totalPricePerSeller,saleAmount);
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
        BuyLog buyLog = new BuyLog(LocalDateTime.now(),totalPrice,saleAmount,findDistinctSellers(super.cart),receiverInformation);
        storage.addBuyLog(buyLog);
        ((Customer)super.person).addToBuyLogs(buyLog);
    }

    public void createSellLog (Seller seller, HashMap<String,String> receiverInformation , double totalPrice , double saleAmount){
        SellLog sellLog = new SellLog(LocalDateTime.now(),totalPrice,saleAmount, (Customer) super.person);
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
}
