package controller;
import controller.*;
import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminManagerTests {

    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);
    private AdminManager adminManager = new AdminManager();

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
        Person person = storage.getUserByUsername("s1");
        storage.getAllUsers().remove(person);
        ArrayList<Person> expected = new ArrayList<>();
        expected.addAll(storage.getAllUsers());
        storage.addUser(person);
        adminManager.deleteUser("s1");
        ArrayList<Person> original = new ArrayList<>();
        original.addAll(storage.getAllUsers());
        Assert.assertArrayEquals(new ArrayList[]{expected}, new ArrayList[]{original});
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
        fileSaver.dataReader();
        Product product = storage.getProductById(2);
        adminManager.removeProduct("2");
        ArrayList<Product> original = storage.getAllProducts();
        Assert.assertFalse(original.contains(product));
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
        Category category = new Category("Housing");
        storage.addCategory(category);
        adminManager.editCategoryByName("Housing","Cloth");
        Assert.assertEquals("Cloth",category.getCategoryName());
        Assert.assertNotNull(storage.getCategoryByName("Cloth"));
    }

    /*@Test
    public void removeCategoryTest() throws Exception {
        Category category = new Category("Housing");
        storage.addCategory(category);
        adminManager.removeCategory("Housing");
        //Assert.assertFalse(storage.getAllCategories().contains(category));
        Assert.assertNull(storage.getCategoryByName("Housing"));
        try {
            adminManager.removeCategory("Housing");
        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"There is not a category with this name!!");
        }
    }*/

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
}
