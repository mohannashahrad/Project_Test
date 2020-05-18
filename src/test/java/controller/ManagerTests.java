package controller;

import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ManagerTests {
    Manager manager = new Manager();
    AdminManager adminManager = new AdminManager();
    Storage storage = Storage.getStorage();

    @Test
    public void checkValidityTest() {
        Assert.assertTrue(manager.checkValidity("jaj17_su8"));
        Assert.assertFalse(manager.checkValidity("jsj@he4dk'd$0_d"));
    }

    @Test
    public void checkEmailValidityTest() {
        Assert.assertTrue(manager.checkEmailValidity("mohanna@gmail.com"));
        Assert.assertFalse(manager.checkEmailValidity("mohanna.gmail.com"));
    }

    @Test
    public void checkPhoneNumberValidityTest() {
        Assert.assertTrue(manager.checkPhoneNumberValidity("09123873653"));
        Assert.assertFalse(manager.checkPhoneNumberValidity("8837-0283"));
    }

    @Test
    public void doesUsernameExistTest() {
        Assert.assertTrue(manager.doesUsernameExist("s1"));
        Assert.assertFalse(manager.doesUsernameExist("s8"));
    }

    @Test
    public void doesDiscountExistTest() {
        Assert.assertFalse(manager.doesDiscountExist("mohanna"));
        adminManager.createDiscountCode("managerTest", LocalDateTime.now(), LocalDateTime.now(), 10,
                2, 20.0);
        Assert.assertTrue(manager.doesDiscountExist("managerTest"));
    }

    @Test
    public void viewCategoryTest() throws Exception {
        try {
            manager.viewCategory("test");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is not such category");
        }
        adminManager.addCategory("managerTest");
        Assert.assertNotNull(manager.viewCategory("managerTest"));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        try {
            manager.getProductById(100);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Product doesn't exist!");
        }
        Assert.assertNotNull(manager.getProductById(1));
    }

    @Test
    public void doesCategoryExistTest() throws Exception {
        Assert.assertFalse(manager.doesCategoryExist("apProject"));
        adminManager.addCategory("Food");
        Assert.assertTrue(manager.doesCategoryExist("Food"));
    }

    @Test
    public void getPersonTest() {
        manager.setPerson(null);
        Assert.assertNull(manager.getPerson());
        manager.setPerson(storage.getUserByUsername("a1"));
        Assert.assertNotNull(manager.getPerson());
    }

    @Test
    public void registerTest() throws Exception {
        HashMap<String, String> information = new HashMap<>();
        information.put("username", "first*User");
        information.put("password", "1234");
        information.put("name", "Joe");
        information.put("family name", "Bruce");
        information.put("email", "JoeBruce@gmail.com");
        information.put("number", "001234567");
        information.put("balance", "200");
        information.put("role", "customer");
        try {
            manager.register(information);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Username is not valid");
        }
        information.replace("username", "firstUser");
        information.replace("password", "182hd@sj_d");
        try {
            manager.register(information);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Password is not valid");
        }
        information.replace("password", "1234");
        information.replace("email", "mohanna.gmail.com");
        try {
            manager.register(information);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Email is not valid");
        }
        information.replace("email", "JoeBruce@gmail.com");
        information.replace("number", "00123j567");
        try {
            manager.register(information);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Phone Number is not valid");
        }
        information.replace("number", "00123567");
        manager.register(information);
        Assert.assertNotNull(storage.getUserByUsername("firstUser"));
        Assert.assertEquals(storage.getUserByUsername("firstUser").getRole().toString(), "CUSTOMER");
        information.replace("role", "admin");
        information.replace("username", "secondUser");
        manager.register(information);
        Assert.assertNotNull(storage.getUserByUsername("secondUser"));
        Assert.assertEquals(storage.getUserByUsername("secondUser").getRole().toString(), "ADMIN");
        information.replace("role", "seller");
        information.replace("username", "thirdUser");
        manager.register(information);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().equals("register seller") && request.getInformation().
                    get("username").equals("thirdSeller")) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void loginTest() throws Exception {
        try {
            manager.login("ls@js", "928j");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Username is not valid");
        }
        try {
            manager.login("ls_dds", "928%dj");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Password is not valid");
        }
        try {
            manager.login("s1", "928j");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Your password is wrong");
        }
        manager.login("s1", "s1");
        Assert.assertEquals(manager.getPerson(), storage.getUserByUsername("s1"));
    }

    @Test
    public void editFieldTest() throws Exception {
        manager.setPerson(storage.getUserByUsername("c1"));
        try {
            manager.editField("password", "928j@jd");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Password is not valid");
        }
        manager.editField("password", "982");
        Assert.assertEquals(manager.getPerson().getPassword(), "982");
        manager.editField("name", "hanna");
        Assert.assertEquals(manager.getPerson().getName(), "hanna");
        manager.editField("familyName", "Legend");
        Assert.assertEquals(manager.getPerson().getFamilyName(), "Legend");
        try {
            manager.editField("email", "928j.gmail.com");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Email is not valid");
        }
        manager.editField("email", "john@gmail.com");
        Assert.assertEquals(manager.getPerson().getEmail(), "john@gmail.com");
        try {
            manager.editField("number", "928j@jd");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Phone Number is not valid");
        }
        manager.editField("number", "929283");
        Assert.assertEquals(manager.getPerson().getNumber(), "929283");
    }

    @Test
    public void logoutTest() throws Exception {
        manager.login("s1", "s1");
        manager.logout();
        Assert.assertNull(manager.getPerson());
    }
}
