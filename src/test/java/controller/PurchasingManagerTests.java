package controller;
import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

public class PurchasingManagerTests {
    private PurchasingManager purchasingManager = new PurchasingManager();
    private Manager manager = new Manager();
    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);

    @Test
    public void getTotalPriceWithoutDiscountTest(){
        fileSaver.dataReader();
        HashMap<Product, Integer> productsInCart = new HashMap<>();
        productsInCart.put(storage.getProductById(2),2);
        productsInCart.put(storage.getProductById(3),3);
        manager.cart.setProductsInCart(productsInCart);
        double expected = 2*12 + 3*21 ;
        double original = purchasingManager.getTotalPriceWithoutDiscount();
        Assert.assertEquals(expected,original,0.0);
    }

    @Test
    public void getDiscountPercentageTest(){
        Discount discount = new Discount("test", LocalDateTime.now(),LocalDateTime.now(),
                20,2,100);
        storage.addDiscount(discount);
        double original = purchasingManager.getDiscountPercentage("");
        double expected = 0.0;
        Assert.assertEquals(original,expected,0.0);
        double secondOriginal = purchasingManager.getDiscountPercentage("test");
        double secondExpected = 20.0;
        Assert.assertEquals(secondExpected,secondExpected,0.0);
    }

    @Test
    public void getProductsInCartTest(){
        HashMap<Product, Integer> productsInCart = new HashMap<>();
        productsInCart.put(storage.getProductById(2),2);
        productsInCart.put(storage.getProductById(3),3);
        manager.cart.setProductsInCart(productsInCart);
        Assert.assertEquals(purchasingManager.getProductsInCart().size(),2);
        Assert.assertEquals(purchasingManager.getProductsInCart().get(0),storage.getProductById(3));
        Assert.assertEquals(purchasingManager.getProductsInCart().get(1),storage.getProductById(2));
    }

    @Test
    public void checkDiscountValidityTest(){
        LocalDateTime firstBeginDate = LocalDateTime.of(2020,06,29,12,20);
        LocalDateTime firstEndDate = LocalDateTime.of(2020,07,29,12,30);
        Discount discount = new Discount("firstTest", firstBeginDate,firstEndDate,
                20,2,100);
        storage.addDiscount(discount);
        try {
            purchasingManager.checkDiscountValidity("firstTest");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"You can't use a discount which is not available yet!");
        }
        LocalDateTime secondBeginDate = LocalDateTime.of(2020,03,29,12,20);
        LocalDateTime secondEndDate = LocalDateTime.of(2020,04,29,12,30);
        Discount secondDiscount = new Discount("secondTest", secondBeginDate,secondEndDate,
                20,2,100);
        storage.addDiscount(secondDiscount);
        try {
            purchasingManager.checkDiscountValidity("secondTest");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"This discount is expired!");
        }
    }

    @Test
    public void findDistinctSellersTest(){
        
    }

}
