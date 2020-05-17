package controller;
import controller.*;
import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AdminManagerTests {

    private Storage storage = Storage.getStorage();
    private Manager manager = new Manager();
    private FileSaver fileSaver = new FileSaver(storage);
    private AdminManager adminManager = new AdminManager();
    private SellerManager sellerManager = new SellerManager();

    @Test
    public void viewUserTest() throws Exception{
        fileSaver.dataReader();
        Person expected = storage.getUserByUsername("s1");
        Assert.assertEquals(expected,adminManager.viewUser("s1"));
        try {
            Assert.assertNotEquals(expected, adminManager.viewUser("s7"));
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"There is not such user!!");
        }
    }

    @Test
    public void deleteUserTest() throws Exception {
        fileSaver.dataReader();
        Person person = storage.getUserByUsername("s2");
        int index = storage.getAllUsers().lastIndexOf(person);
        storage.getAllUsers().remove(person);
        ArrayList<Person> expected = new ArrayList<>(storage.getAllUsers());
        storage.getAllUsers().add(index, person);
        adminManager.deleteUser("s2");
        ArrayList<Person> original = new ArrayList<>(storage.getAllUsers());
        for (int counter = 0; counter < expected.size(); counter++){
            Assert.assertEquals(expected.get(counter), original.get(counter));
        }
        try {
            adminManager.deleteUser("s7");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"There is not such user!!");
        }
    }
    @Test
    public void createManagerTest() throws Exception{
        fileSaver.dataReader();
        HashMap<String,String> information = new HashMap<>();
        information.put("username", "firstUser");
        information.put("password", "1234");
        information.put("name", "Joe");
        information.put("family name", "Bruce");
        information.put("email", "JoeBruce@gmail.com");
        information.put("number", "001234567");
        information.put("balance", "200");
        information.put("role", "admin");
        Admin firstAdmin = new Admin(information);
        storage.addUser(firstAdmin);
        ArrayList<Person> expected = storage.getAllUsers();
        storage.getAllUsers().remove(firstAdmin);
        adminManager.createManager(information);
        ArrayList<Person> original = storage.getAllUsers();
        Assert.assertArrayEquals(new ArrayList[]{expected}, new ArrayList[]{original});
        information.replace("username","heh@");
        try {
            adminManager.createManager(information);
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Username is not valid!!");
        }
        information.replace("username","firstUser");
        information.replace("password","828@");
        try {
            adminManager.createManager(information);
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Password is not valid!!");
        }
        information.replace("password", "1234");
        information.replace("email","hsh.s");
        try {
            adminManager.createManager(information);
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email is not valid");
        }
        information.replace("number", "1234hd");
        information.replace("email","mohannashahrd@gmail.com");
        try {
            adminManager.createManager(information);
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Phone Number is not valid");
        }
    }

    @Test
    public void removeProductTest() throws Exception{
        ArrayList<Product> first = storage.getAllProducts();
        adminManager.removeProduct("1");
        ArrayList<Product> second = storage.getAllProducts();
        Assert.assertArrayEquals(new ArrayList[]{first}, new ArrayList[]{second});
        try {
            adminManager.removeProduct("9");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"There is not such product!!");
        }
    }

    @Test
    public void addCategoryTest() throws Exception {
        fileSaver.dataReader();
        adminManager.addCategory("Housing");
        Assert.assertNotNull(storage.getCategoryByName("Housing"));
        Category expected = storage.getCategoryByName("Housing");
        Assert.assertTrue(storage.getAllCategories().contains(expected));
        try {
            adminManager.addCategory("Housing");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Category with this name already exists!!");
        }
    }

    @Test
    public void editCategoryByNameTest(){
        fileSaver.dataReader();
        Category category = new Category("Clothing");
        storage.addCategory(category);
        adminManager.editCategoryByName("Clothing","Babies");
        Assert.assertEquals("Babies",category.getCategoryName());
        Assert.assertNotNull(storage.getCategoryByName("Babies"));
    }

    @Test
    public void removeCategoryTest() throws Exception {
        Category category = new Category("Gardening");
        storage.addCategory(category);
        adminManager.removeCategory("Gardening");
        Assert.assertNull(storage.getCategoryByName("Gardening"));
        try {
            adminManager.removeCategory("Gardening");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"There is not a category with this name!!");
        }
    }

    @Test
    public void editDiscountFieldTest(){
        fileSaver.dataReader();
        Discount discount = new Discount("test", LocalDateTime.now(),LocalDateTime.now(),
                20,2,100);
        storage.addDiscount(discount);
        adminManager.editDiscountField(discount,"percentage", "30");
        double expectedPercentage = Double.parseDouble("30.0");
        Assert.assertEquals(discount.getPercentage(),expectedPercentage,0.0);
        adminManager.editDiscountField(discount,"usagePerCustomer", "3");
        int expectedUsagePerCustomer = Integer.parseInt("3");
        Assert.assertEquals(discount.getUsageCount(),expectedUsagePerCustomer);
        LocalDateTime expectedBeginDate = LocalDateTime.of(2020,5,28,12,20);
        adminManager.editDiscountField(discount,"beginDate", "2020-05-28 12:20");
        Assert.assertEquals(discount.getBeginDate(),expectedBeginDate);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020,8,28,12,20);
        adminManager.editDiscountField(discount,"endDate", "2020-08-28 12:20");
        Assert.assertEquals(discount.getEndDate(),expectedEndDate);
        adminManager.editDiscountField(discount,"maxAmount", "200.0");
        double expectedMaxAmount = Double.parseDouble("200.0");
        Assert.assertEquals(discount.getMaxAmount(),expectedMaxAmount,0.0);


    }

    @Test
    public void editProductTest() throws Exception {
        fileSaver.dataReader();
        Seller seller = (Seller) storage.getUserByUsername("s1");
        HashMap<String,String> productInformation = new HashMap<>();
        productInformation.put("productId", "5");
        productInformation.put("name", "sweater");
        productInformation.put("brand", "GAP");
        productInformation.put("price", "280");
        productInformation.put("supply", "2" );
        productInformation.put("categoryName", "clothing");
        productInformation.put("explanation", "warm and cozy");
        productInformation.put("seller","s1");
        Product product = new Product(productInformation,seller);
        storage.addProduct(product);
        Request request = new Request("add product",productInformation);
        storage.addRequest(request);
        adminManager.acceptRequest(Integer.toString(request.getRequestId()));
        adminManager.editProduct("5","name","pants");
        Assert.assertEquals(product.getName(),"pants");
        adminManager.editProduct("5","brand","Adidas");
        Assert.assertEquals(product.getBrand(),"Adidas");
        adminManager.editProduct("5","price","300");
        Assert.assertEquals(product.getPrice(),Double.parseDouble("300.0"),0.0);
        adminManager.editProduct("5","supply","4");
        Assert.assertEquals(product.getSupply(),Integer.parseInt("4"));
        adminManager.editProduct("5","categoryName","Children");
        Assert.assertEquals(product.getCategory(),Category.getCategoryByName("Children"));
        adminManager.editProduct("5","explanation","Good");
        Assert.assertEquals(product.getExplanation(),"Good");;
    }

    @Test
    public void editSaleTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm");
        Sale sale = new Sale(LocalDateTime.now(),LocalDateTime.now(),20,null);
        storage.addSale(sale);
        adminManager.editSale(sale.getSaleId(),"beginDate","2020,05,28,12,20");
        Assert.assertEquals(sale.getBeginDate(),LocalDateTime.parse("2020,05,28,12,20", formatter));
        adminManager.editSale(sale.getSaleId(),"endDate","2020,08,28,12,20");
        Assert.assertEquals(sale.getEndDate(),LocalDateTime.parse("2020,08,28,12,20", formatter));
        adminManager.editSale(sale.getSaleId(),"amountOfSale","30");
        Assert.assertEquals(sale.getAmountOfSale(),30);
    }

    @Test
    public void addCommentRequest(){
        fileSaver.dataReader();
        HashMap<String,String> information = new HashMap<>();
        information.put("productId","2");
        information.put("title","Really bad");
        information.put("content","Don't ever use it");
        information.put("username","c1");
        Request request = new Request("ADD_COMMENT",information);
        adminManager.addCommentRequest(request);
        Product product = storage.getProductById(2);
        Comment comment = new Comment("c1",product,"Really bad","Don't ever use it");
        for (Comment productComment : product.getComments()) {
            if (productComment.equals(comment)){
                Assert.assertTrue(productComment.equals(comment));
            }
        }
    }

    @Test
    public void processAcceptRequestRegisterSeller(){
        HashMap<String,String> information = new HashMap<>();
        information.put("username", "sellerUser");
        information.put("password", "5678");
        information.put("name", "Jack");
        information.put("family name", "Fallon");
        information.put("email", "JackFallon@gmail.com");
        information.put("number", "001987654");
        information.put("balance", "100");
        information.put("role", "seller");
        information.put("company", "Best Products");
        Request request = new Request("register seller",information);
        adminManager.processAcceptedRequest(request);
        Assert.assertNotNull(storage.getUserByUsername("sellerUser"));
    }

    @Test
    public void addSaleRequestTest(){
        manager.setPerson(storage.getUserByUsername("s1"));
        ArrayList<Product> productsInSale = new ArrayList<>(Arrays.asList(storage.getProductById(1),storage.getProductById(2)));
        sellerManager.getSavedProductsInSale().put(1, productsInSale);
        HashMap<String,String> saleInformation = new HashMap<>();
        saleInformation.put("username","s1");
        saleInformation.put("beginDate","2020,07,01,12,20");
        saleInformation.put("endDate","2020,09,01,12,20");
        saleInformation.put("amountOfSale","30");
        saleInformation.put("offId","1");
        Request request = new Request("add sale",saleInformation);
        adminManager.processAcceptedRequest(request);
        Assert.assertEquals(storage.getSaleById(1).getAmountOfSale(),30);
        Assert.assertEquals("2020-08-28T12:20", storage.getSaleById(1).getEndDate().toString());
    }
}
