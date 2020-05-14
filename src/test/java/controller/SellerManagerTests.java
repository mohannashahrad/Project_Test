package controller;

import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class SellerManagerTests {
    private Storage storage = Storage.getStorage();
    private Manager manager = new Manager();
    private FileSaver fileSaver = new FileSaver(storage);
    private SellerManager sellerManager = new SellerManager();

    @Test
    public void loginTest() throws Exception {
        fileSaver.dataReader();
        Seller seller = (Seller) manager.login("s1", "s1");
        sellerManager.setPerson(seller);
        try {
            seller = (Seller) manager.login("s2", "s1");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Your password is wrong");
        }
        try {
            seller = (Seller) manager.login("**", "s1");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Username is not valid");
        }
        try {
            seller = (Seller) manager.login("s1", "**");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Password is not valid");
        }
    }

    @Test
    public void viewSellerProduct() throws Exception {
        loginTest();
        Product expected = sellerManager.getSellerProductById(2);
        Assert.assertEquals(expected, sellerManager.viewSellerProduct(2));
        try {
            Assert.assertNotEquals(expected, sellerManager.viewSellerProduct(7));
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "A product with this Id does not exist!!");
        }
        try {
            Assert.assertNotEquals(expected.getSeller(), storage.getProductById(3).getSeller());
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "You don't have this product!!");
        }
    }

    /*@Test
    public void addProduct() throws Exception {
        loginTest();
        HashMap<String, String> information = new HashMap<>();
        information.put("name", "sneakers");
        information.put("brand", "Nike");
        information.put("price", "200");
        information.put("supply", "100");
        information.put("categoryName", "clothing");
        information.put("explanation", "women's sneakers");
        sellerManager.addProduct(information);
        Request request = new Request("add product",information);
        Assert.assertTrue(Request.getAllRequests().contains(request));
    }*/

    @Test
    public void doesSellerHaveProductTest() throws Exception {
        loginTest();
        Assert.assertTrue(sellerManager.doesSellerHaveProduct(2));
        Assert.assertFalse(sellerManager.doesSellerHaveProduct(3));
    }
    @Test
    public void viewSellerProducts() throws Exception {
        fileSaver.dataReader();
        loginTest();
        ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(storage.getProductById(2)));
        Assert.assertEquals(allProducts, sellerManager.viewSellerProducts());
    }
}