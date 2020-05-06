package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Person {
    private Cart shoppingCart; /*when to new this?!*/
    private ArrayList<BuyLog> buyHistory;
    private ArrayList<Discount> allDiscounts;

    public Customer(HashMap<String, String> information) {
        super(information);
        this.buyHistory = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public ArrayList<BuyLog> getBuyHistory() {
        return buyHistory;
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public void addToAllDiscounts(Discount newDiscount) {
        this.allDiscounts.add(newDiscount);
    }

    public void removeFromAllDiscounts(Discount specificDiscount) {
        allDiscounts.removeIf(specificDiscount::equals);
    }

    public void addToBuyLogs(BuyLog newBuyLog) {
        buyHistory.add(newBuyLog);
    }
    public Cart newCartForThisCustomer(){
        this.shoppingCart = Cart.getCart();
        return this.shoppingCart;
    }
}
