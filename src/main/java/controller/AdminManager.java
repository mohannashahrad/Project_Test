package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.*;
import java.util.Date;
import java.util.HashMap;

public class AdminManager extends Manager {
    public AdminManager() {
    }

    public ArrayList<Person> viewAllUsers (){
        return storage.getAllUsers();
    }

    public Person viewUser (String username){
        return storage.getUserByUsername(username);
    }

    public void deleteUser (String username) throws Exception {
        if (storage.getUserByUsername(username) == null)
            throw new Exception("There is not such user");
        else
        storage.deleteUser(storage.getUserByUsername(username));
    }

    public void createManager (HashMap<String,String> information) throws Exception {
        if (!checkValidity(information.get("username")))
            throw new Exception("Username is not valid");
        else if (!checkValidity(information.get("password")))
            throw new Exception("Password is not valid");
        else
            storage.addUser(new Person (information));
    }

    public void removeProduct (String productId) throws Exception {
        if(storage.getProductById(productId) == null)
            throw new Exception("There is not such product");
        else
        storage.deleteProduct(storage.getProductById(productId));
    }

    public ArrayList<Discount> viewAllDiscountCodes (){
        return storage.getAllDiscounts();
    }

    public ArrayList<Request> vieAllRequests (){
        return storage.getAllRequests();
    }

    public Discount viewDiscountCode (String code){
        return storage.getDiscountByCode(code);
    }

    public void createDiscountCode (String code , Date startDate , Date endDate , double amount) throws Exception {
        if (storage.getDiscountByCode(code) != null)
            throw new Exception("Discount already exists");
        else
        storage.addDiscount(new Discount(code,startDate,endDate,amount));
    }

    public void removeDiscountCode (String code) throws Exception {
        if (storage.getDiscountByCode(code) == null)
            throw new Exception("There is not such Discount Code");
        else
        storage.deleteDiscount(storage.getDiscountByCode(code));
    }

    public Request viewRequest (String requestId){
        return storage.getRequestById(Integer.parseInt(requestId));
    }

    public void removeCategory (String name) throws Exception {
        if (storage.getCategoryByName(name) == null)
            throw new Exception("There is not a category with this name");
        else
        storage.deleteCategory(storage.getCategoryByName(name));
    }

    public void acceptRequest (String requestId) throws Exception {
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            throw new Exception("There is not a request with this Id");
        else {
            storage.getRequestById(Integer.parseInt(requestId)).acceptRequest();
            processAcceptedRequest(storage.getRequestById(Integer.parseInt(requestId)));
        }
    }

    public void declineRequest (String requestId) throws Exception {
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            throw new Exception("There is not a request with this Id");
        else
        storage.getRequestById(Integer.parseInt(requestId)).declineRequest();
    }

    private void processAcceptedRequest (Request request){
        switch (request.getTypeOfRequest()) {
            case REGISTER_SELLER:
                storage.addUser(new Person (request.getInformation()));
            case ADD_PRODUCT:
                storage.addProduct(new Product (request.getInformation()));
            case EDIT_PRODUCT:
                String productField = request.getInformation().get("field");
                String productUpdatedVersion = request.getInformation().get("updatedVersion");
                String productId = request.getInformation().get("productId");
                editProduct(productId,productField,productUpdatedVersion);
            case EDIT_SALE:
                String saleField = request.getInformation().get("field");
                String saleUpdatedVersion = request.getInformation().get("updatedVersion");
                String saleId = request.getInformation().get("productId");
                try {
                    editSale(saleId,saleField,saleUpdatedVersion);
                } catch (ParseException e) {
                    e.getMessage();
                }
            case REMOVE_PRODUCT:
                storage.deleteProduct(storage.getProductById(request.getInformation().get("productId")));
        }
    }

    public void editProduct (String productId, String field, String updatedVersion){
        if (field.equalsIgnoreCase("name"))
            storage.getProductById(productId).setName(updatedVersion);
        else if (field.equalsIgnoreCase("brand"))
            storage.getProductById(updatedVersion).setBrand(updatedVersion);
        else if (field.equalsIgnoreCase("price"))
            storage.getProductById(productId).setPrice(Double.parseDouble(updatedVersion));
        else if (field.equalsIgnoreCase("explanation"))
            storage.getProductById(productId).setExplanation(updatedVersion);
    }

    private void editSale (String saleId, String field, String updatedVersion) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        if (field.equalsIgnoreCase("beginDate"))
            storage.getSaleById(saleId).setBeginDate(formatter.parse(updatedVersion));
        if (field.equalsIgnoreCase("endDate"))
            storage.getSaleById(saleId).setEndDate(formatter.parse(updatedVersion));
        if (field.equalsIgnoreCase("amountOfSale"))
            storage.getSaleById(saleId).setAmountOfSale(Double.parseDouble(updatedVersion));
    }
}
