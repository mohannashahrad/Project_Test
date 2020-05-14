package controller;
import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

public class CustomerManagerTests {

    private CustomerManager customerManager = new CustomerManager();
    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);

}
