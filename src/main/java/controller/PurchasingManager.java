package controller;

import model.BuyLog;
import model.*;

import java.time.LocalDateTime;
import java.util.HashMap;

public class PurchasingManager extends Manager{

    public PurchasingManager() {
    }

    // No idea what to do for creating logs!!!
    public void performPayment(HashMap<String,String> receiverInformation , double totalPrice , double saleAmount){
        super.person.setBalance(super.person.getBalance() - totalPrice);
        for (Product product : super.cart.getProductsInCart().keySet()){
            product.getSeller().setBalance(product.getSeller().getBalance() + product.getPrice());
            storage.addSellLog(new SellLog(LocalDateTime.now(),product.getPrice(),saleAmount,((Customer)super.person)));
            storage.addBuyLog(new BuyLog(LocalDateTime.now(),totalPrice,saleAmount,product.getSeller(),receiverInformation));
        }
    }
}
