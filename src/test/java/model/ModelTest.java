package model;

import controller.FileSaver;
import controller.Storage;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class ModelTest {
    private HashMap<String, String> productInformation = new HashMap<>();
    private HashMap<String, String> informationAdmin = new HashMap<>();
    private Storage storage = new Storage();
    private FileSaver fileSaver = new FileSaver(storage);

    public HashMap<String, String> productInformation(HashMap<String, String> information) {
        information.put("name", "bag");
        information.put("brand", "LV");
        information.put("price", "200");
        information.put("supply", "10");
        information.put("categoryName", "uncategorized");
        information.put("explanation", "Shoulder Bag");

        return information;
    }

    public HashMap<String, String> addToAdminInformation(HashMap<String, String> information) {
        information.put("username", "adminUser");
        information.put("password", "5764");
        information.put("name", "Alex");
        information.put("family name", "Nick");
        information.put("email", "alexN@gmail.com");
        information.put("number", "001937654");
        information.put("balance", "43");
        information.put("role", "admin");
        return information;
    }

    private Admin testAdmin = new Admin(addToAdminInformation(informationAdmin));

    @Test
    public void addToCartTest() {
        fileSaver.dataReader();
        Seller firstSeller = (Seller) storage.getUserByUsername("s1");
        Customer testCustomer = (Customer) storage.getUserByUsername("c1");
        Customer testCustomer2 = (Customer) storage.getUserByUsername("c2");
        Product firstProduct = storage.getProductById(1);
        Product secondProduct = storage.getProductById(2);
        Cart newCart1 = new Cart(testCustomer);
        Cart newCart2 = new Cart(testCustomer2);
        HashMap<String, String> customerInfo = new HashMap<>();
        customerInfo.put("address", "Tehran");
        customerInfo.put("Phone number", "11");
        ArrayList<Seller> sellers = new ArrayList<>();
        sellers.add(firstSeller);
        newCart1.addProductToCart(firstProduct);
        newCart1.addProductToCart(secondProduct);
        newCart1.decreaseProduct(firstProduct);
        double actual = newCart1.getTotalPrice();
        ArrayList<Product> productsInCart = new ArrayList<>(newCart1.getProductsInCart().keySet());
        Assert.assertEquals(productsInCart, Collections.singletonList(secondProduct));
        Assert.assertEquals(secondProduct.getPrice(), actual, 0.0);
        BuyLog firstBuyLog = new BuyLog(LocalDateTime.now(), newCart1.getTotalPrice(), 0.0,
                sellers, customerInfo, productsInCart);
        testCustomer.addToBuyLogs(firstBuyLog);
        ArrayList<BuyLog> buyLogs = new ArrayList<>();
        buyLogs.add(firstBuyLog);
        Assert.assertEquals(buyLogs, testCustomer.getBuyHistory());
        SellLog firstLog = new SellLog(LocalDateTime.now(), newCart1.getTotalPrice(), 0, testCustomer);
        firstSeller.addToSellLogs(firstLog);
        secondProduct.addBuyer(testCustomer);
        Assert.assertEquals(testCustomer, newCart1.getCustomer());
        newCart2.addProductToCart(firstProduct);
        newCart2.addProductToCart(secondProduct);
        firstProduct.addBuyer(testCustomer2);
        secondProduct.addBuyer(testCustomer2);
        SellLog secondLog = new SellLog(LocalDateTime.now(), newCart2.getTotalPrice(), 0, testCustomer2);
        firstSeller.addToSellLogs(secondLog);
        Assert.assertEquals(Collections.singletonList(testCustomer2), firstProduct.getThisProductsBuyers());
        Assert.assertEquals(0, storage.getProductById(1).getSupply());
        Assert.assertEquals(0, storage.getProductById(2).getSupply());
        Assert.assertEquals(Arrays.asList(firstLog, secondLog), firstSeller.getSellHistory());
    }

    @Test
    public void sellerProductTest() {
        fileSaver.dataReader();
        Seller firstSeller = (Seller) storage.getUserByUsername("s1");
        Product firstProduct = storage.getProductById(1);
        Product secondProduct = storage.getProductById(2);
        Comment firstComment = new Comment("user", storage.getProductById(1),
                "First Comment", "The style was not really good.");
        Comment secondComment = new Comment("person", storage.getProductById(1),
                "Second Comment", "I didn't buy it.It was not that good.");

        Assert.assertEquals("company", firstSeller.getCompany());
        firstProduct.addComment(firstComment);
        firstProduct.addComment(secondComment);
        String expectedTitle = "First Comment";
        Assert.assertEquals(expectedTitle, firstComment.getCommentTitle());
        String expectedBody = "The style was not really good.";
        Assert.assertEquals(expectedBody, firstComment.getCommentBody());
        Assert.assertEquals(Arrays.asList(firstComment, secondComment).toString(), firstProduct.getComments().toString());

        /*secondProduct.addRate(firstRate);
        secondProduct.addRate(secondRate);
        double expectedAverageRate = (4 + 4.5) / 2;
        secondProduct.calculateAverageRate();
        Assert.assertEquals(expectedAverageRate, secondProduct.getAverageRate(), 0.2);
        Assert.assertEquals(Arrays.asList(firstRate, secondRate), secondProduct.getRates());*/

        Comment checkEqualityComment = new Comment("user", firstProduct,
                "First Comment", "The style was not really good.");
        Assert.assertEquals(firstComment, checkEqualityComment);
    }

    @Test
    public void checkProduct() {
        fileSaver.dataReader();
        Product firstProduct = storage.getProductById(1);
        Product secondProduct = storage.getProductById(2);
        Product thirdProduct = storage.getProductById(3);
        Seller seller = (Seller) storage.getUserByUsername("s1");

        Assert.assertEquals(firstProduct, storage.getProductById(1));
        Assert.assertEquals(0, firstProduct.compareTo(secondProduct));
        Assert.assertEquals(1, thirdProduct.compareTo(secondProduct));
        Assert.assertEquals(-1, firstProduct.compareTo(thirdProduct));

        Product product = new Product(productInformation(productInformation), seller);
        Assert.assertEquals(6, product.getProductId());
        seller.removeProduct(product);
        Assert.assertNull(storage.getProductById(6));
    }

    @Test
    public void sortAndFilterTest() {
        fileSaver.dataReader();
        Sort sort = new Sort("sort");

        ArrayList<Product> sortedOrFilteredProducts = new ArrayList<>();
        sortedOrFilteredProducts.add(storage.getProductById(1));
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(4));
        sortedOrFilteredProducts.add(storage.getProductById(3));
        sortedOrFilteredProducts.add(storage.getProductById(5));
        Assert.assertEquals(sortedOrFilteredProducts, sort.sortByPrice(storage.getAllProducts()));

        storage.getProductById(3).addRate(new Rate("c1", storage.getProductById(3), 4.2));
        storage.getProductById(3).calculateAverageRate();
        storage.getProductById(1).addRate(new Rate("c1", storage.getProductById(1), 3.6));
        storage.getProductById(1).calculateAverageRate();
        storage.getProductById(5).addRate(new Rate("c1", storage.getProductById(5), 4.0));
        storage.getProductById(5).calculateAverageRate();
        storage.getProductById(2).addRate(new Rate("c1", storage.getProductById(5), 4.5));
        storage.getProductById(2).calculateAverageRate();
        storage.getProductById(4).addRate(new Rate("c1", storage.getProductById(4), 3.5));
        storage.getProductById(4).calculateAverageRate();
        sortedOrFilteredProducts.clear();
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(3));
        sortedOrFilteredProducts.add(storage.getProductById(5));
        sortedOrFilteredProducts.add(storage.getProductById(1));
        sortedOrFilteredProducts.add(storage.getProductById(4));
        Assert.assertEquals(sortedOrFilteredProducts, sort.sortByAverageRate(storage.getAllProducts()));

        storage.getProductById(4).setNumberOfSeen(5);
        storage.getProductById(2).setNumberOfSeen(8);
        storage.getProductById(3).setNumberOfSeen(4);
        storage.getProductById(5).setNumberOfSeen(6);
        storage.getProductById(1).setNumberOfSeen(4);
        sortedOrFilteredProducts.clear();
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(5));
        sortedOrFilteredProducts.add(storage.getProductById(4));
        sortedOrFilteredProducts.add(storage.getProductById(3));
        sortedOrFilteredProducts.add(storage.getProductById(1));
        Assert.assertEquals(sortedOrFilteredProducts, sort.defaultSort(storage.getAllProducts()));

        Filter filter = new Filter("filter", "filter by name, price, category");
        Category clothingCategory = new Category("clothing");
        Category drinksCategory = new Category("drinks");
        storage.getProductById(2).setCategory(clothingCategory);
        storage.getProductById(5).setCategory(drinksCategory);
        storage.getProductById(3).setCategory(clothingCategory);
        storage.getProductById(1).setCategory(drinksCategory);
        storage.getProductById(4).setCategory(clothingCategory);
        sortedOrFilteredProducts.clear();
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(4));
        sortedOrFilteredProducts.add(storage.getProductById(3));
        Assert.assertEquals(sortedOrFilteredProducts, filter.filterByCategory(clothingCategory, storage.getAllProducts()));

        sortedOrFilteredProducts.clear();
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(1));
        Assert.assertEquals(sortedOrFilteredProducts, filter.filterByName("p11", storage.getAllProducts()));

        sortedOrFilteredProducts.clear();
        sortedOrFilteredProducts.add(storage.getProductById(2));
        sortedOrFilteredProducts.add(storage.getProductById(4));
        sortedOrFilteredProducts.add(storage.getProductById(1));
        Assert.assertEquals(sortedOrFilteredProducts, filter.filterByPrice(10.0, storage.getAllProducts()));

        storage.getAllCategories().add(clothingCategory);
        storage.getAllCategories().add(drinksCategory);
        Assert.assertTrue(Category.doesCategoryExist("drinks"));
        Assert.assertFalse(Category.doesCategoryExist("furniture"));
        Assert.assertEquals(clothingCategory, Category.getCategoryByName("clothing"));
        HashMap<String, String> properties = new HashMap<>();
        properties.put("special property", "for children");
        properties.put("ordinary property", "cheap");
        clothingCategory.addNewProperty("special property", "for children");
        clothingCategory.addNewProperty("ordinary property", "cheap");
        Assert.assertEquals(properties, clothingCategory.getProperties());

        properties.replace("special property", "good quality");
        clothingCategory.setSingleValueInProperties("special property", "good quality");
        ArrayList<Product> clothingCategoryProducts = new ArrayList<>();
        clothingCategoryProducts.add(storage.getProductById(1));
        clothingCategoryProducts.add(storage.getProductById(2));
        clothingCategoryProducts.add(storage.getProductById(4));
        clothingCategory.addToThisCategoryProducts(storage.getProductById(1));
        clothingCategory.addToThisCategoryProducts(storage.getProductById(2));
        clothingCategory.addToThisCategoryProducts(storage.getProductById(4));
        Assert.assertEquals(clothingCategoryProducts, clothingCategory.getThisCategoryProducts());
    }

    @Test
    public void requestTest() {
        fileSaver.dataReader();
        Request registerSellerRequest = new Request("register seller", productInformation);
        Request addProductRequest = new Request("add product", productInformation);
        Request editProductRequest = new Request("edit product", productInformation);
        Request removeProductRequest = new Request("remove product", productInformation);
        Request addSaleRequest = new Request("add sale", productInformation);
        Request editSaleRequest = new Request("edit sale", productInformation);
        Request addProductToSaleRequest = new Request("add product to sale", productInformation);
        Request removeProductFromSaleRequest = new Request("remove product from sale", productInformation);
        Request addCommentRequest = new Request("add comment", productInformation);

        Assert.assertEquals(RequestType.REGISTER_SELLER, registerSellerRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.ADD_PRODUCT, addProductRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.EDIT_PRODUCT, editProductRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.REMOVE_PRODUCT, removeProductRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.ADD_SALE, addSaleRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.EDIT_SALE, editSaleRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.ADD_PRODUCT_TO_SALE, addProductToSaleRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.REMOVE_PRODUCT_FROM_SALE, removeProductFromSaleRequest.getTypeOfRequest());
        Assert.assertEquals(RequestType.ADD_COMMENT, addCommentRequest.getTypeOfRequest());

        Assert.assertEquals(StateType.PROCESSING, addProductRequest.getStateOfRequest());
        addProductRequest.declineRequest();
        Assert.assertEquals(StateType.DECLINED, addProductRequest.getStateOfRequest());
        addProductRequest.acceptRequest();
        Assert.assertEquals(StateType.ACCEPTED, addProductRequest.getStateOfRequest());
    }

    @Test
    public void saleDiscountCheck() {
        fileSaver.dataReader();
        Seller seller = (Seller) storage.getUserByUsername("s1");
        ArrayList<Product> firstSaleProducts = new ArrayList<>();
        firstSaleProducts.add(storage.getProductById(1));
        Sale firstSale = new Sale(LocalDateTime.now(), LocalDateTime.now(), 30, firstSaleProducts);
        seller.addSale(firstSale);
        Assert.assertNotNull(((Seller) storage.getUserByUsername("s1")).getSaleList());
        Assert.assertEquals(60.0, firstSale.calculateAmountOfSale(200.0), 0.01);
        firstSale.addProductToThisSale(storage.getProductById(2));
        firstSaleProducts.add(storage.getProductById(2));
        Assert.assertEquals(firstSaleProducts, firstSale.getProductsWithThisSale());
        firstSale.removeProductFromThisSale(storage.getProductById(1));
        Assert.assertFalse(firstSale.getProductsWithThisSale().contains(storage.getProductById(1)));
        firstSale.addProductToThisSale(storage.getProductById(1));
        ArrayList<Product> secondSaleProducts = new ArrayList<>();
        secondSaleProducts.add(storage.getProductById(2));
        secondSaleProducts.add(storage.getProductById(3));
        secondSaleProducts.add(storage.getProductById(4));
        Sale secondSale = new Sale(LocalDateTime.now(), LocalDateTime.now(), 50, secondSaleProducts);
        ArrayList<Sale> allSales = new ArrayList<>();
        allSales.add(firstSale);
        allSales.add(secondSale);
        Sale.removeProductFromItSale(allSales, storage.getProductById(2));
        Assert.assertFalse(firstSaleProducts.contains(storage.getProductById(2)));
        Assert.assertFalse(secondSaleProducts.contains(storage.getProductById(2)));

        Discount discount = new Discount("MH17", LocalDateTime.now(), LocalDateTime.now(), 40,
                1, 300);
        discount.addCustomer((Customer) storage.getUserByUsername("c1"));
        discount.addCustomer((Customer) storage.getUserByUsername("c2"));
        HashMap<Customer, Integer> thisDiscountCustomers = new HashMap<>();
        thisDiscountCustomers.put((Customer) storage.getUserByUsername("c1"), 1);
        thisDiscountCustomers.put((Customer) storage.getUserByUsername("c2"), 1);
        Assert.assertEquals(thisDiscountCustomers, discount.getCustomersWithThisDiscount());
        discount.removeCustomer((Customer) storage.getUserByUsername("c1"), 1);
        Assert.assertFalse(discount.getCustomersWithThisDiscount().containsKey((Customer) storage.getUserByUsername("c1")));
    }

    @Test
    public void equalityOfDiscountTest(){
        Discount firstDiscount = new Discount("code1",LocalDateTime.now(),LocalDateTime.now(),
                12,2,100);
        Discount secondDiscount = new Discount("code2",LocalDateTime.now(),LocalDateTime.now(),
                12,2,100);
        Assert.assertFalse(firstDiscount.equals(secondDiscount));
    }
    
}
