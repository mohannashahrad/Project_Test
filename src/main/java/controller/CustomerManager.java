package controller;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerManager extends Manager {

    public CustomerManager() {
    }

    public Cart getCart (){
        return super.cart;
    }

    public HashMap<Product , Integer> getProductsInCart(){
        return super.cart.getProductsInCart();
    }

    public Product getProductInCart(int productId) throws Exception {
        if(!super.cart.getProductsInCart().containsKey(storage.getProductById(productId)))
            throw new Exception("You don't have such product in your cart!");
        else
            return storage.getProductById(productId);
    }

    public boolean doesProductExist (int productId){
        if (storage.getProductById(productId) == null)
            return false;
        else return true;
    }

    public void addBalance (double money){
        super.person.setBalance(super.person.getBalance() + money);
    }

    public void increaseProduct(String productId) throws Exception {
        if(storage.getProductById(Integer.parseInt(productId)) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(Integer.parseInt(productId))))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.addNumberOfProductInTheCart(storage.getProductById(Integer.parseInt(productId)));
    }

    public void decreaseProduct(String productId) throws Exception {
        if(storage.getProductById(Integer.parseInt(productId)) == null)
            throw new Exception("There is not such product!");
        else if (!super.cart.getProductsInCart().containsKey(storage.getProductById(Integer.parseInt(productId))))
            throw new Exception("You don't have a product with this Id in your cart!");
        else
            super.cart.decreaseProduct(storage.getProductById(Integer.parseInt(productId)));
    }

    public double getCartTotalPrice(){
        return super.cart.getTotalPrice();
    }

    public void addProductToCart(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not a product with this Id!!");
        if (super.cart == null) {
            super.cart = new Cart(null);
            storage.addCart(super.cart);
        }
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

    public void rateProduct(int productId , double rate) throws Exception {
        if(!storage.getProductById(productId).getThisProductsBuyers().contains(super.person))
            throw new Exception("You can't rate a product which you didn't buy it!!");
        else {
            Rate rateOfThisProduct = new Rate (super.person.getUsername(),storage.getProductById(productId),rate);
            storage.addRate(rateOfThisProduct);
            storage.getProductById(productId).addRate(rateOfThisProduct);
        }
    }

    public ArrayList<BuyLog> getCustomerBuyLogs(){
        return ((Customer)super.person).getBuyHistory();
    }


}
