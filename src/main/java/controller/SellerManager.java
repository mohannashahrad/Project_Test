package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.*;

public class SellerManager extends Manager {
    public SellerManager() {
    }

    public void registerSeller(HashMap<String,String> information){
        storage.addRequest(new Request("register seller" , information));
    }

    public Product viewSellerProduct (String productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("A product with this Id does noe exist!!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have this product!!");
        else
            return storage.getProductById(productId);
    }

    public void addProduct (HashMap<String,String> information) throws Exception {
        if (storage.getProductById(information.get("productId")) != null)
            throw new Exception("A product exists with same productId. Please select another Id.");
        else
            storage.addRequest(new Request("add product" , information));
    }

    public void removeProduct (String productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else {
            HashMap<String,String> information = new HashMap<>();
            information.put("productId",productId);
            storage.addRequest(new Request("remove product",information));
        }
    }

    public ArrayList<Customer> viewProductBuyers(String productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else
            return storage.getProductById(productId).getThisProductsBuyers();
    }

    public void editProduct(String productId , String field , String updatedVersion) throws Exception{
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else {
            HashMap<String,String> information = new HashMap<>();
            information.put(field,updatedVersion);
            storage.addRequest(new Request("edit product", information));
        }
    }

    public ArrayList<Sale> viewSellerOffs (){
        return ((Seller)super.person).getSaleList();
    }

    public void addOff(HashMap<String,String> information) throws Exception {
        storage.addRequest(new Request("add sale" , information));
    }

}
