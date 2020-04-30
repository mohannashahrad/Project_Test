package controller;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerManager extends Manager {

    public CustomerManager() {
    }

    public Cart viewCart (){
        return super.cart;
    }

    public HashMap<Product , Integer> showProductsInCart(){
        return super.cart.getProductsInCart();
    }

    public Product viewProductInCart(String productId) throws Exception {
        if(!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have such product in your cart!");
        else
            return storage.getProductById(productId);
    }

    public void increaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.addNumberOfProductInTheCart(storage.getProductById(productId));
    }

    public void decreaseProduct(String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.decreaseProduct(storage.getProductById(productId));
    }

    public double getCartTotalPrice(){
        return super.cart.getTotalPrice();
    }

    public void addProductToCart(String productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not a product with this Id!!");
        else if(super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You already have this product in you cart.Please use the increase[productId] command!");
        else
            super.cart.addProductToCart(storage.getProductById(productId));
    }

    public ArrayList<Discount> getCustomerDiscountCodes (){
        return ((Customer)super.person).getAllDiscounts();
    }

    public BuyLog getOrderWithId(String orderId){
        return storage.getBuyLogByCode(orderId);
    }

    public void rateProduct(String productId , double rate) throws Exception {
        if(!storage.getProductById(productId).getThisProductsBuyers().contains(super.person))
            throw new Exception("You can't rate a product which you didn't buy it!!");
        else {
            Rate rateOfThisProduct = new Rate (super.person.getUserName(), storage.getProductById(productId), rate);
            storage.addRate(rateOfThisProduct);
            storage.getProductById(productId).addRate(rateOfThisProduct);
        }
    }

    public ArrayList<BuyLog> getCustomerBuyLogs(){
        return ((Customer)super.person).getBuyHistory();
    }
    
    public void checkDiscountValidity(String discountCode) throws Exception {
        if (storage.getDiscountByCode(discountCode).getEndDate().isBefore(LocalDateTime.now()))
            throw new Exception("This discount is expired!");
        else if (storage.getDiscountByCode(discountCode).getBeginDate().isAfter(LocalDateTime.now()))
            throw new Exception("You can't use a discount which is not available yet!");
        else
            throw new Exception("You can use this discount . Enjoy it :)");
    }

    public boolean doesCustomerHaveEnoughMoney(double price){
        if (super.person.getBalance() < price)
            return false;
        else
            return true;
    }
}
