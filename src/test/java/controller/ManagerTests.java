package controller;

import model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ManagerTests {
    Manager manager = new Manager();

    @Test
    public void checkValidityTest(){
         Assert.assertTrue(manager.checkValidity("jaj17_su8"));
         Assert.assertFalse(manager.checkValidity("jsj@he4dk'd$0_d"));
    }

    @Test
    public void checkEmailValidityTest(){
        Assert.assertTrue(manager.checkEmailValidity("mohanna@gmail.com"));
        Assert.assertFalse(manager.checkEmailValidity("mohanna.gmail.com"));
    }

}
