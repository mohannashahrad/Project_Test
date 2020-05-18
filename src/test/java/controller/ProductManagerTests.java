package controller;

import model.*;
import controller.*;
import org.junit.Assert;
import org.junit.Test;

public class ProductManagerTests {
    private ProductManager productManager = new ProductManager();
    private Manager manager = new Manager();
    private Storage storage = Storage.getStorage();
    private FileSaver fileSaver = new FileSaver(storage);

    @Test
    public void addCommentTest() {
        String content = "Low quality . never use it!";
        String title = "Not Good";
        manager.setPerson(null);
        productManager.addComment(1, title, content);
        for (Request request : storage.getAllRequests()) {
            if (request.getTypeOfRequest().equals("ADD_COMMENT")) {
                if (request.getInformation().get("title").equals(title) && request.getInformation().get("content").equals(content))
                    Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void compareTwoProductsTest() throws Exception {
        try {
            productManager.compareTwoProducts(1, 1);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "These products are the same!");
        }
        try {
            productManager.compareTwoProducts(1, 30);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "There is not a product with this Id!");
        }
        String original = productManager.compareTwoProducts(1, 3);
        String expected = "First Product Name : p11 --- Second Product Name : p12" + "\n" +
                "First Product Price : 10.0 --- Second Product Price : 20.0" + "\n" +
                "First Product Seller Name : s1 s1 --- Second Product Seller Name : s1 s1" + "\n" +
                "First Product Average Rate : 0.0 --- Second Product Average Rate : 0.0" + "\n" +
                "First Product Brand : p11 --- Second Product Brand : p12" + "\n" +
                "First Product Explanation: nothing --- Second Product Explanation : nothing" + "\n" +
                "First Product Number Of Available Samples : 1 --- Second Product Number Of Available Samples : 1";
        Assert.assertEquals(expected, original);
    }

    @Test
    public void addNumberOfSeenTest() {
        Product product = storage.getProductById(1);
        product.setNumberOfSeen(2);
        productManager.addNumberOfSeen(1);
        Assert.assertEquals(3, product.getNumberOfSeen());
    }

}
