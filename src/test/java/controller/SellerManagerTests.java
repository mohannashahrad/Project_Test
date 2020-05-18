package controller;

import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class SellerManagerTests {
    private Storage storage = Storage.getStorage();
    private Manager manager = new Manager();
    private AdminManager adminManager = new AdminManager();
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
            sellerManager.getSellerProductById(7);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "A product with this Id does not exist!!");
        }
        try {
            sellerManager.getSellerProductById(3);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "You don't have this product!!");
        }
    }

    @Test
    public void doesSellerHaveProductTest() throws Exception {
        loginTest();
        Assert.assertTrue(sellerManager.doesSellerHaveProduct(2));
        Assert.assertFalse(sellerManager.doesSellerHaveProduct(4));
    }

    @Test
    public void addProductTest() {
        manager.setPerson(storage.getUserByUsername("s1"));
        HashMap<String, String> informationForAddedProduct = new HashMap<>();
        informationForAddedProduct.put("name", "shoes");
        informationForAddedProduct.put("brand", "NIKE");
        informationForAddedProduct.put("price", "123");
        informationForAddedProduct.put("supply", "1");
        informationForAddedProduct.put("categoryName", "clothing");
        informationForAddedProduct.put("explanation", "orthopedic shoes");
        sellerManager.addProduct(informationForAddedProduct);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().toString().equals("add product")) {
                if (request.getInformation().get("seller").equals("s1") && request.getInformation().get("productId").equals("10")) {
                    Assert.assertTrue(true);
                }
            }
        }
    }

    @Test
    public void removeProductTest() throws Exception {
        manager.setPerson(storage.getUserByUsername("s1"));
        try {
            sellerManager.removeProduct(10);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is not such product!");
        }
        try {
            sellerManager.removeProduct(3);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "You don't have such product!");
        }
        sellerManager.removeProduct(2);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().equals(RequestType.REMOVE_PRODUCT)) {
                if (request.getInformation().get("username").equals("s1") && request.getInformation().get("productId").equals("2")) {
                    Assert.assertTrue(true);
                }
            }
        }
    }

    @Test
    public void editProductTest() throws Exception {
        manager.setPerson(storage.getUserByUsername("s1"));
        try {
            sellerManager.editProduct(10, "price", "30");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is not such product!");
        }
        try {
            sellerManager.editProduct(3, "price", "30");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "You don't have such product!");
        }
        sellerManager.editProduct(1, "price", "30");
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().toString().equals("edit product")) {
                if (request.getInformation().get("field").equals("price") && request.getInformation().get("productId").
                        equals("1") && request.getInformation().get("updatedVersion").equals("30")) {
                    Assert.assertTrue(true);
                }
            }
        }
    }

    @Test
    public void sellerOffTest() throws Exception {
        manager.setPerson(storage.getUserByUsername("s1"));
        ArrayList<Product> productsInSale = new ArrayList<>();
        productsInSale.add(storage.getProductById(1));
        productsInSale.add(storage.getProductById(2));
        HashMap<String, String> saleInformation = new HashMap<>();
        saleInformation.put("beginDate", "2020,07,01,12,20");
        saleInformation.put("endDate", "2020,09,01,12,20");
        saleInformation.put("amountOfSale", "20");
        sellerManager.addOff(saleInformation, productsInSale);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().equals(RequestType.ADD_SALE)) {
                if (request.getInformation().get("username").equals("s1") && request.getInformation().get("offId").
                        equals("1") && request.getInformation().get("amountOfSale").equals("20")) {
                    adminManager.acceptRequest(Integer.toString(request.getRequestId()));
                    Assert.assertTrue(true);
                }
            }
        }
        try {
            sellerManager.editOff(3, "endDate", "2020,09,20,12,20");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is no such off!");
        }
        sellerManager.editOff(1, "amountOfSale", "40");
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().toString().equals("edit sale")) {
                if (request.getInformation().get("offId").equals("1") && request.getInformation().get("amountOfSale").
                        equals("40")) {
                    Assert.assertTrue(true);
                    adminManager.acceptRequest(Integer.toString(request.getRequestId()));
                }
            }
        }
        Assert.assertNotNull(storage.getSaleById(1));
        Sale original = sellerManager.viewSingleOff(1);
        Assert.assertEquals(original, storage.getSaleById(1));
        try {
            sellerManager.viewSingleOff(2);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is not such off!");
        }
        try {
            sellerManager.removeProductFromOff(1, 30);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is no product with this Id!");
        }
        try {
            sellerManager.removeProductFromOff(10, 1);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is no off with this Id");
        }
        try {
            sellerManager.removeProductFromOff(1, 3);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "This product is not assigned to this sale!");
        }
        sellerManager.removeProductFromOff(1, 1);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().equals(RequestType.REMOVE_PRODUCT_FROM_SALE)) {
                if (request.getInformation().get("productId").equals("1") && request.getInformation().get("offId").
                        equals("1")) {
                    Assert.assertTrue(true);
                }
            }
        }
        try {
            sellerManager.addProductToOff(1, 1);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "This product is already added in this sale!");
        }
        try {
            sellerManager.addProductToOff(2, 1);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is no off with this Id");
        }
        try {
            sellerManager.addProductToOff(1, 19);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is no product with this Id!");
        }
    }

    @Test
    public void doesProductExistTest() {
        Assert.assertTrue(sellerManager.doesProductExist(1));
        Assert.assertFalse(sellerManager.doesProductExist(100));
    }
}