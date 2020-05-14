package controller;
import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class CustomerManagerTests {
    Manager manager = new Manager();
    private CustomerManager customerManager = new CustomerManager();
    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);

    @Test
    public void loginTest() throws Exception {
        fileSaver.dataReader();
        Customer customer = (Customer) manager.login("c1", "c1");
        customerManager.setPerson(customer);
        try {
            customer = (Customer) manager.login("c1", "s1");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Your password is wrong");
        }
        try {
            customer = (Customer) manager.login("**", "s1");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Username is not valid");
        }
        try {
            customer = (Customer) manager.login("c1", "**");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Password is not valid");
        }
    }
    @Test
    public void getProductsInCartTest() throws Exception {
        fileSaver.dataReader();
        loginTest();
        HashMap<Product, Integer> productsInCart = new HashMap<>();
        productsInCart.put(storage.getProductById(3), 1);
        productsInCart.put(storage.getProductById(2), 2);
        customerManager.getProductsInCart().put(storage.getProductById(3), 1);
        customerManager.getProductsInCart().put(storage.getProductById(2), 2);
        Assert.assertEquals(productsInCart, customerManager.getProductsInCart());
        Assert.assertEquals(storage.getProductById(3), customerManager.getProductInCart(3));
        try{
            Assert.assertEquals(storage.getProductById(4),customerManager.getProductInCart(4));
        } catch (Exception e){
            Assert.assertEquals("You don't have such product in your cart!", e.getMessage());
        }
        productsInCart.replace(storage.getProductById(3), 2);
        customerManager.increaseProduct("3");
        Assert.assertEquals(productsInCart, customerManager.getProductsInCart());
        try{
            customerManager.increaseProduct("7");
        } catch (Exception e){
            Assert.assertEquals("There is not such product!",e.getMessage());
        }
        productsInCart.replace(storage.getProductById(2), 1);
        customerManager.decreaseProduct("2");
        Assert.assertEquals(productsInCart,customerManager.getProductsInCart());
        try{
            customerManager.decreaseProduct("4");
        }
        catch (Exception e){
            Assert.assertEquals("You don't have a product with this Id in your cart!", e.getMessage());
        }
        try{
            customerManager.decreaseProduct("7");
        } catch (Exception e){
            Assert.assertEquals("There is not such product!",e.getMessage());
        }
    }

    @Test
    public void doesProductExistTest(){
        Assert.assertTrue(customerManager.doesProductExist(3));
        Assert.assertFalse(customerManager.doesProductExist(7));
    }

    @Test
    public void addBalanceTest() throws Exception {
        loginTest();
        customerManager.addBalance(200.45);
        Assert.assertEquals(200.45, customerManager.getPerson().getBalance(),0.0);
    }
}
