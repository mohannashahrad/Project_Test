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

    public boolean deleteUser (String username){
        if (storage.getUserByUsername(username) == null)
            return false;
        storage.deleteUser(storage.getUserByUsername(username));
        return true;
    }

    public void createManager (HashMap<String,String> information) throws Exception {
        if (!checkValidity(information.get("username")))
            throw new Exception("Username is not valid");
        else if (!checkValidity(information.get("password")))
            throw new Exception("Password is not valid");
        else
            storage.addUser(new Person (information));
    }

    public boolean removeProduct (String productId){
        if(storage.getProductById(productId) == null)
            return false;
        storage.deleteProduct(storage.getProductById(productId));
        return true;
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

    public boolean createDiscountCode (String code , Date startDate , Date endDate , double amount){
        if (storage.getDiscountByCode(code).equals(code))
            return false;
        storage.addDiscount(new Discount(code,startDate,endDate,amount));
        return true;
    }

    public boolean removeDiscountCode (String code){
        if (storage.getDiscountByCode(code) == null)
            return false;
        storage.deleteDiscount(storage.getDiscountByCode(code));
        return true;
    }

    public Request viewRequest (String requestId){
        return storage.getRequestById(Integer.parseInt(requestId));
    }

    public boolean removeCategory (String name){
        if (storage.getCategoryByName(name) == null)
            return false;
        storage.deleteCategory(storage.getCategoryByName(name));
        return true;
    }

    public boolean acceptRequest (String requestId){
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            return false;
        storage.getRequestById(Integer.parseInt(requestId)).acceptRequest();
        processAcceptedRequest(storage.getRequestById(Integer.parseInt(requestId)));
        return true;
    }

    public boolean declineRequest (String requestId){
        if(storage.getRequestById(Integer.parseInt(requestId)) == null)
            return false;
        storage.getRequestById(Integer.parseInt(requestId)).declineRequest();
        return true;
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
