package controller;
import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        productsInCart.put(storage.getProductById(1),2);
        productsInCart.put(storage.getProductById(2),3);
        manager.cart.setProductsInCart(productsInCart);
        double expected = 2*10 + 3*10 ;
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
        productsInCart.put(storage.getProductById(1),2);
        productsInCart.put(storage.getProductById(2),3);
        manager.cart.setProductsInCart(productsInCart);
        Assert.assertEquals(purchasingManager.getProductsInCart().size(),2);
        Assert.assertEquals(purchasingManager.getProductsInCart().get(0),storage.getProductById(1));
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
    public void calculateTotalPriceWithDiscountTest(){
        fileSaver.dataReader();
        HashMap<Product, Integer> productsInCart = new HashMap<>();
        productsInCart.put(storage.getProductById(1),2);
        productsInCart.put(storage.getProductById(2),3);
        manager.cart.setProductsInCart(productsInCart);
        LocalDateTime firstBeginDate = LocalDateTime.of(2020,06,29,12,20);
        LocalDateTime firstEndDate = LocalDateTime.of(2020,07,29,12,30);
        Discount discount = new Discount("discount1", firstBeginDate,firstEndDate,
                20,2,100);
        storage.addDiscount(discount);
        double expected = (2*10 + 3*10) * (0.8) ;
        double original = purchasingManager.calculateTotalPriceWithDiscount("discount1");
        Assert.assertEquals(expected,original,0.0);
    }

    @Test
    public void findDistinctSellersTest() {
        fileSaver.dataReader();
        HashMap<Product, Integer> products = new HashMap<>();
        products.put(storage.getProductById(1), 2);
        products.put(storage.getProductById(2), 3);
        manager.cart.setProductsInCart(products);
        ArrayList<Seller> original = purchasingManager.findDistinctSellers(manager.cart);
        ArrayList<Seller> expected = new ArrayList<>();
        expected.add((Seller)storage.getUserByUsername("s1"));
        Assert.assertArrayEquals(new ArrayList[]{expected}, new ArrayList[]{original});
    }

    @Test
    public void sellerProductsInCartTest(){
        fileSaver.dataReader();
        HashMap<Product, Integer> productsFromDifferentSellers = new HashMap<>();
        productsFromDifferentSellers.put(storage.getProductById(1), 2);
        productsFromDifferentSellers.put(storage.getProductById(2), 3);
        manager.cart.setProductsInCart(productsFromDifferentSellers);
        ArrayList <Product> original = purchasingManager.sellerProductsInCart(manager.cart,
                (Seller) storage.getUserByUsername("s1"));
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(storage.getProductById(1));
        expected.add(storage.getProductById(2));
        Assert.assertArrayEquals(new ArrayList[]{expected}, new ArrayList[]{original});
    }

    @Test
    public void calculateEachSellerMoneyTransferTest(){
        ArrayList<Product> productsToBeCalculated = new ArrayList<>();
        productsToBeCalculated.add(storage.getProductById(1));
        productsToBeCalculated.add(storage.getProductById(2));
        double expected = purchasingManager.calculateEachSellerMoneyTransfer(productsToBeCalculated);
        double original = 20.0;
        Assert.assertEquals(expected,original,0.0);
    }

    @Test
    public void createBuyLogTest(){
        fileSaver.dataReader();
        HashMap<Product, Integer> productsCart = new HashMap<>();
        productsCart.put(storage.getProductById(1), 2);
        productsCart.put(storage.getProductById(2), 3);
        manager.cart.setProductsInCart(productsCart);
        manager.setPerson(storage.getUserByUsername("c1"));
        HashMap<String,String> receiverInformation = new HashMap<>();
        receiverInformation.put("name","mohanna");
        receiverInformation.put("address","tehran");
        ArrayList<Product> productsInLog = new ArrayList<>();
        productsInLog.add(storage.getProductById(1));
        productsInLog.add(storage.getProductById(2));
        ArrayList<Seller> sellers = new ArrayList<>();
        sellers.add((Seller)storage.getUserByUsername("s1"));
        BuyLog buyLog = new BuyLog(LocalDateTime.now(),20.0,10.0,sellers,receiverInformation,
                productsInLog);
        purchasingManager.createBuyLog(receiverInformation,20.0,10.0);
        for (Log log : storage.getAllBuyLogs()) {
            if (log.equals(buyLog))
                Assert.assertTrue(true);
        }

    }

    @Test
    public void displayDetailsOfProductTest(){
        HashMap<Product,Integer> products = new HashMap<>();
        products.put(storage.getProductById(1),1);
        manager.cart.setProductsInCart(products);
        String original = purchasingManager.displayDetailsOfProduct(storage.getProductById(1));
        String expected = "p11 -- 10.0 -- 0.0 -- 10.0 -- 1";
    }

    @Test
    public void doesCustomerHaveEnoughMoneyTest(){
        manager.setPerson(storage.getUserByUsername("c1"));
        Assert.assertFalse(purchasingManager.doesCustomerHaveEnoughMoney(30.0));
    }
    @Test
    public void MoneyToTransferTest(){
        double totalPrice = 20;
        int discountPercentage = 10;
        double moneyToTransfer =   totalPrice - totalPrice*(1.0*discountPercentage/100);
      Assert.assertEquals(moneyToTransfer,18,0.0);
    }
    @Test
    public void performPaymentTest(){

    }

}
