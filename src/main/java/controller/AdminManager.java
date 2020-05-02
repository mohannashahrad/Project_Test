package controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.*;
import java.util.HashMap;

public class AdminManager extends Manager {
    public AdminManager() {
    }

    public ArrayList<Person> viewAllUsers (){
        return storage.getAllUsers();
    }

    public Person viewUser (String username) throws Exception {
        if (storage.getUserByUsername(username) == null)
            throw new Exception("There is not such user!!");
        else
        return storage.getUserByUsername(username);
    }

    public void deleteUser (String username) throws Exception {
        if (storage.getUserByUsername(username) == null)
            throw new Exception("There is not such user!!");
        else
        storage.deleteUser(storage.getUserByUsername(username));
    }

    public void createManager (HashMap<String,String> information) throws Exception {
        if (!checkValidity(information.get("username")))
            throw new Exception("Username is not valid!!");
        else if (!checkValidity(information.get("password")))
            throw new Exception("Password is not valid!!");
        else
            storage.addUser(new Person (information));
    }

    public void removeProduct (String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product!!");
        else
        storage.deleteProduct(storage.getProductById(productId));
    }

    public void addCategory (String name, HashMap<String,String> properties,ArrayList<Product> productsInThisCategory)
            throws Exception {
        if(storage.getCategoryByName(name) != null)
            throw new Exception("Category with this name already exists!!");
        else
            storage.addCategory(new Category(name,productsInThisCategory,properties));
    }

    public ArrayList<Discount> viewAllDiscountCodes (){
        return storage.getAllDiscounts();
    }

    public ArrayList<Request> viewAllRequests (){
        return storage.getAllRequests();
    }

    public Discount viewDiscountCode (String code) throws Exception {
        if (storage.getDiscountByCode(code) == null)
            throw new Exception("There is not such discount code!!");
        else
        return storage.getDiscountByCode(code);
    }

    public void addCustomerToDiscount(String username , Discount discount) throws Exception {
        if (storage.getUserByUsername(username) == null)
            throw new Exception("There is not a user with this username!");
        else if (discount.getCustomersWithThisDiscount().containsKey(storage.getUserByUsername(username)))
            throw new Exception("This customer is already added to this discount!");
        else
            discount.addCustomer((Customer) storage.getUserByUsername(username));
    }

    public void removeCustomerFromDiscount (Discount discount , String username) throws Exception {
        if (storage.getUserByUsername(username) == null)
            throw new Exception("There is not a user with this username!");
        else if (!discount.getCustomersWithThisDiscount().containsKey(storage.getUserByUsername(username)))
            throw new Exception("This customer does not have this discount!!");
        else
            discount.removeCustomer((Customer) storage.getUserByUsername(username),discount.getUsageCount());
    }

    public void editDiscountField (String field , String updatedVersion , Discount discount){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch (field){
            case "percentage" :
                discount.setPercentage(Integer.parseInt(updatedVersion));
            case "usagePerCustomer":
                discount.setUsageCount(Integer.parseInt(updatedVersion));
            case "beginDate":
                LocalDateTime beginDate = LocalDateTime.parse(updatedVersion,formatter);
                discount.setBeginDate(beginDate);
            case "endDate":
                LocalDateTime endDate = LocalDateTime.parse(updatedVersion,formatter);
                discount.setEndDate(endDate);
            case "maxAmount":
                discount.setMaxAmount(Double.parseDouble(updatedVersion));
        }
    }

    public boolean doesDiscountCodeExist (String code){
        if (storage.getDiscountByCode(code) == null)
            return false;
        else return true;
    }

    public void createDiscountCode (String code, LocalDateTime startDate, LocalDateTime endDate,
                                    int percentage, int usagePerCustomer, double maxAmount,
                                    HashMap<Customer, Integer> customersWithThisDiscount){
        storage.addDiscount(new Discount(code,startDate,endDate,percentage,usagePerCustomer,customersWithThisDiscount,maxAmount));
    }

    public void removeDiscountCode (String code) throws Exception {
        if (storage.getDiscountByCode(code) == null)
            throw new Exception("There is not such Discount Code!!");
        else
        storage.deleteDiscount(storage.getDiscountByCode(code));
    }

    public Request viewRequest (String requestId) throws Exception {
        if (storage.getRequestById(Integer.parseInt(requestId)) == null)
            throw new Exception("There is not such request!!");
        else
        return storage.getRequestById(Integer.parseInt(requestId));
    }

    public void removeCategory (String name) throws Exception {
        if (storage.getCategoryByName(name) == null)
            throw new Exception("There is not a category with this name!!");
        else
        storage.deleteCategory(storage.getCategoryByName(name));
    }

    public void editCategoryByName (String oldName , String newName){
            storage.getCategoryByName(oldName).setCategoryName(newName);
    }

    public boolean doesCategoryExist (String name){
        if (storage.getCategoryByName(name) == null)
            return false;
        else return true;
    }

    public void editCategoryByProperties (Category category ,String property, String newValue){
        if(!category.getProperties().containsKey(property))
            category.addNewProperty(property,newValue);
        else
            category.setSingleValueInProperties(property,newValue);
    }

    public void acceptRequest (String requestId) throws Exception {
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            throw new Exception("There is not a request with this Id!!");
        else {
            storage.getRequestById(Integer.parseInt(requestId)).acceptRequest();
            processAcceptedRequest(storage.getRequestById(Integer.parseInt(requestId)));
        }
    }

    public void declineRequest (String requestId) throws Exception {
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            throw new Exception("There is not a request with this Id!!");
        else
        storage.getRequestById(Integer.parseInt(requestId)).declineRequest();
    }

    public void createAvailableFilters (ArrayList<String> filterTags){
        for (String tag : filterTags) {
            storage.addFilter(new Filter (tag));
        }
    }

    public void createAvailableSorts (ArrayList<String> sortTags){
        for (String tag : sortTags) {
            storage.addSort(new Sort(tag));
        }
    }

    private void processAcceptedRequest (Request request){
        switch (request.getTypeOfRequest()) {
            case REGISTER_SELLER:
                storage.addUser(new Seller(request.getInformation()));
            case ADD_PRODUCT:
                Seller seller = (Seller) storage.getUserByUsername(request.getInformation().get("seller"));
                Product product = new Product(request.getInformation(),seller);
                storage.addProduct(product);
                seller.addProduct(product);
            case ADD_SALE:
                addSaleRequest(request);
            case EDIT_PRODUCT:
                String productField = request.getInformation().get("field");
                String productUpdatedVersion = request.getInformation().get("updatedVersion");
                String productId = request.getInformation().get("productId");
                editProduct(productId,productField,productUpdatedVersion);
            case EDIT_SALE:
                String saleField = request.getInformation().get("field");
                String saleUpdatedVersion = request.getInformation().get("updatedVersion");
                int saleId = Integer.parseInt(request.getInformation().get("offId"));
                try {
                    editSale(saleId,saleField,saleUpdatedVersion);
                } catch (ParseException e) {
                    e.getMessage();
                }
            case REMOVE_PRODUCT:
                Sale sale = null;
                Product removedProduct = storage.getProductById(request.getInformation().get("productId"));
                storage.deleteProduct(removedProduct);
                ((Seller)storage.getUserByUsername(request.getInformation().get("username"))).removeProduct(removedProduct);
                sale.removeProductFromItSale(storage.getAllSales(),removedProduct);
        }
    }

    private void editProduct (String productId, String field, String updatedVersion){
        if (field.equalsIgnoreCase("name"))
            storage.getProductById(productId).setName(updatedVersion);
        else if (field.equalsIgnoreCase("brand"))
            storage.getProductById(updatedVersion).setBrand(updatedVersion);
        else if (field.equalsIgnoreCase("price"))
            storage.getProductById(productId).setPrice(Double.parseDouble(updatedVersion));
        else if (field.equalsIgnoreCase("explanation"))
            storage.getProductById(productId).setExplanation(updatedVersion);
        else if (field.equalsIgnoreCase("supply"))
            storage.getProductById(productId).setSupply(Integer.parseInt(updatedVersion));
        else if (field.equalsIgnoreCase("categoryName"))
            storage.getProductById(productId).setExplanation(updatedVersion);
    }

    private void editSale (int offId, String field, String updatedVersion) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (field.equalsIgnoreCase("beginDate"))
            storage.getSaleById(offId).setBeginDate(LocalDateTime.parse(updatedVersion, formatter));
        if (field.equalsIgnoreCase("endDate"))
            storage.getSaleById(offId).setEndDate(LocalDateTime.parse(updatedVersion, formatter));
        if (field.equalsIgnoreCase("amountOfSale"))
            storage.getSaleById(offId).setAmountOfSale(Integer.parseInt(updatedVersion));
    }

    private void addSaleRequest (Request request){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime beginDate = LocalDateTime.parse(request.getInformation().get("beginDate"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(request.getInformation().get("endDate"),formatter);
        int amountOfOff = Integer.parseInt(request.getInformation().get("amountOfSale"));
        int offId = Integer.parseInt(request.getInformation().get("offId"));
        Sale sale = new Sale(beginDate,endDate,amountOfOff,sellerManager.getSavedProductsInSale().get(offId));
        storage.addSale(sale);
        ((Seller)storage.getUserByUsername(request.getInformation().get("username"))).addSale(sale);
    }

}
